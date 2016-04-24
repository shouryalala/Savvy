package catch22.com.savvy.popup;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import catch22.com.savvy.EditString;
import catch22.com.savvy.R;
import catch22.com.savvy.background_process.BackgroundService;
import files.FileHandler;

import static catch22.com.savvy.EditString.app_name_message;
import static catch22.com.savvy.EditString.edited_out;

public class PopupActivity extends Activity
{
    public static String user_input ="";
    public static String app_name = "";
    public static String string;

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