package catch22.com.savvy.tutorial;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import catch22.com.savvy.R;

/**
 * Created by root on 24/4/16.
 */
public class ApiCatcher extends AppCompatActivity {

    static final String API_KEY = "&apikey=240b9794-10cf-4371-afdd-3f2fdad16080";
    static final String API_URL = "https://api.havenondemand.com/1/api/sync/extractconcepts/v1?text=";
    String url = "";
    String query = "";
    String jsonStr = "";
    Button gets,Flush;
    EditText search;
    TextView tvDisplay;
    ProgressBar progressBar;
    public static String json_query = "http://";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_getter);
        gets = (Button) findViewById(R.id.btGetter);
        Flush = (Button) findViewById(R.id.Flusher);
        tvDisplay = (TextView) findViewById(R.id.tvJSON);
        search = (EditText) findViewById(R.id.etQuery);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Flush.setEnabled(false);
        gets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query += search.getText().toString();
                query = query.replace(" ", "+");
                Log.d("MainAct", query);
                new AsyncCaller().execute();
            }
        });

        Flush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

    }

    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected Void doInBackground(Void... params) {
            try {
                url += API_URL + query + API_KEY;
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
            Log.d("Response: ", "> " + jsonStr);
            progressBar.setVisibility(View.GONE);
            if(jsonStr.equals(""))
                Toast.makeText(getApplicationContext(), "Connection Unsuccessful", Toast.LENGTH_SHORT).show();
            else {
                Toast.makeText(getApplicationContext(), "Connection Successful", Toast.LENGTH_SHORT).show();
                try{
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray jsonArray = jsonObject.getJSONArray("concepts");
                    for (int  i = 0; i < jsonArray.length(); i++) {
                        JSONObject content = jsonArray.getJSONObject(i);
                        tvDisplay.setText(content.getString("concept")+"\n");
                        json_query = content.getString("concept");
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                Flush.setEnabled(true);
            }

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //startService(new Intent(MainActivity.this, BackService.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
