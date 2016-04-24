package catch22.com.savvy.popup;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import catch22.com.savvy.EditString;
import catch22.com.savvy.R;
import catch22.com.savvy.SettingsActivity;
import catch22.com.savvy.background_process.BackgroundService;
import files.FileHandler;

import static catch22.com.savvy.EditString.app_name_message;
import static catch22.com.savvy.EditString.edited_out;

public class PopupActivity extends Activity
{
    public static String user_input ="";
    public static String app_name = "";
    public static String string;
    static final String API_KEY = "&apikey=240b9794-10cf-4371-afdd-3f2fdad16080";
    static final String API_URL = "https://api.havenondemand.com/1/api/sync/extractconcepts/v1?text=";
    String url = "";
    String jsonStr = "";
    public static String keywords = "";

    private SearchView queryEditor;

    private TextView solutionResult;
    private TextView loadPreviousResultButton;
    private Button smallButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        queryEditor = (SearchView) findViewById(R.id.editText);

        queryEditor.setIconifiedByDefault(false);

        solutionResult = (TextView) findViewById(R.id.solutionResult);

        queryEditor.setVisibility(View.VISIBLE);
        solutionResult.setVisibility(View.GONE);

        loadPreviousResultButton = (TextView) findViewById(R.id.loadPreviousResultButton);

        loadPreviousResultButton.setVisibility(View.VISIBLE);

        smallButton = (Button) findViewById(R.id.searchOnGoogleButton);
        smallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                searchGoogle(user_input + " " + app_name_message);
                queryEditor.setVisibility(View.VISIBLE);
                solutionResult.setVisibility(View.GONE);

                loadPreviousResultButton.setVisibility(View.VISIBLE);

                smallButton.setVisibility(View.GONE);
            }
        });


        smallButton.setVisibility(View.GONE);

        queryEditor.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String lastActivity = BackgroundService.recent;
                String[] packageNameParts = lastActivity.split("\\.");

                user_input = queryEditor.getQuery().toString();
                app_name = packageNameParts[packageNameParts.length - 1].toLowerCase();
                edited_out();

                queryEditor.setVisibility(View.GONE);
                solutionResult.setVisibility(View.VISIBLE);
                loadPreviousResultButton.setVisibility(View.GONE);

                if(SettingsActivity.internetServiceEnabled && BackgroundService.isNetWorkAvailable){
                    user_input = user_input.replace(" ","+");
                    new AsyncCaller().execute();

                }


                string = EditString.user_solution(PopupActivity.this);

                if(string.equals("qwertyuiopasdfghjklzxcvbnm"))
                {
                    string = "";
                    solutionResult.setText("We don't have the answer to that. Would you like to Google it instead?");
                    smallButton.setVisibility(View.VISIBLE);
                    return false;
                }

                solutionResult.setText(string);
                FileHandler.write(PopupActivity.this);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        loadPreviousResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryEditor.setVisibility(View.GONE);
                solutionResult.setVisibility(View.VISIBLE);
                v.setVisibility(View.GONE);
                FileHandler.read(PopupActivity.this);
                solutionResult.setText(string);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_popup, menu);
        return true;
    }

    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            try {
                url += API_URL + user_input + API_KEY;
                Log.d("URL->", url);
                URL website = new URL(url);
                Log.d("After before", "getJsonString ");
                URLConnection connection = website.openConnection();
                Log.d("After open", "getJsonString ");
                connection.setRequestProperty("Accept-Charset", "UTF-8");
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null)
                    response.append(inputLine);

                in.close();
                jsonStr += response.toString();
                Log.d("response", "doInBackground " + jsonStr);

            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            Log.d("Response: ", "> " + jsonStr);;
            if(jsonStr.equals(""))
                Toast.makeText(getApplicationContext(), "Connection Unsuccessful", Toast.LENGTH_SHORT).show();
            else {
                Toast.makeText(getApplicationContext(), "Connection Successful", Toast.LENGTH_SHORT).show();
                try{
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray jsonArray = jsonObject.getJSONArray("concepts");
                    for (int  i = 0; i < jsonArray.length(); i++) {
                        JSONObject content = jsonArray.getJSONObject(i);
                        keywords += content.getString("concept")+" ";
                        Log.d("KEYWORDS->",keywords);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void searchGoogle(String b)
    {
        String a = b.replace(" ","+");
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.google.co.in/search?q="+a/*+"&ie=utf-8&oe=utf-8&gws_rd=cr&ei=vlgcV92RFsWtmAWd6KC4Cw"*/));
        startActivity(i);
    }
}