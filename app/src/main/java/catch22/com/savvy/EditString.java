package catch22.com.savvy;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import catch22.com.savvy.DBEssentials.GetAllEntries;
import catch22.com.savvy.DBEssentials.LoginDataBaseAdapter;
import catch22.com.savvy.popup.PopupActivity;

public class EditString
{
    public static String message; //the full message
    public static String app_name_message; //just the app name
    public static String key_selected; //create
    public static String task_selected; //contact
    public static StringBuffer spaceSeparatedTasksInUsersQuery = new StringBuffer(); //idk
    public static List<String> tasksInUsersQuery = new ArrayList<String>(); //the tasks inside the queries
    private static LoginDataBaseAdapter loginDataBaseAdapter;


    public static List<String> ignore=new ArrayList<String>( Arrays.asList("a","of","to","the","how","do","i"));
    public static List<String> keyword=new ArrayList<String>( Arrays.asList("create","change","make","modify","update","add","view","save","delete","exit","leave","invite","star","see","unstar","remove","use","setup","attach","send","open","call","dial","import","sort","enable","disable","check","search","write","text","select","deselect","get","lower","decrease","increase","start","stop","switch","toggle","set","document"));
//    public static List<String> apps = new ArrayList<String>(Arrays.asList("whatsapp", "dialer", "messaging", "launcher"));
    public static ArrayList<ArrayList<String>> apps = new ArrayList<>();
    static
    {
        ArrayList<String> whatsapp = new ArrayList<>(Arrays.asList("whatsapp"));
        ArrayList<String> dialer = new ArrayList<>(Arrays.asList("dialer", "phoneapp", "caller", "dialpad"));
        ArrayList<String> messaging = new ArrayList<>(Arrays.asList("messaging", "messenger", "messages", "sms"));
        ArrayList<String> launcher = new ArrayList<>(Arrays.asList("launcher", "home", "googlequicksearchbox", "android"));
        apps.add(whatsapp);
        apps.add(dialer);
        apps.add(messaging);
        apps.add(launcher);
    }
    public static List<String> task= new ArrayList<String>(Arrays.asList("group","admin","message","status","contact","dp","number","account","admin group","friend","notification tone","message tone","tone","alert tone","message starred","list message star","web whatsapp","photo","video","multiple photo","dial speed","speeddial","call history","someone","sim","sms","ringtone","contacts","format name","call vibration","balance","conversation","chat","message new","smiley","emoticon","face sad","picture","video","audio","delivery report","wallpaper","brightess","data","wifi","internet","bluetooth"));

    private static List<String> wordsOfTheMessage; //ik
    private static int numberOfWordsInTheMessage, numberOfTasksInUsersQuery;
    private static boolean iHaveTheSolution; //google or not to google

    public static void edited_out()
    {
        app_name_message = PopupActivity.app_name;

        for(ArrayList<String> appNameSynonyms : apps)
        {
            for(String appName : appNameSynonyms)
            {
                Log.d("Levenshtein Distance", appName + " " + app_name_message + " " + computeLevenshteinDistance(appName, app_name_message));
                if (computeLevenshteinDistance(appName, app_name_message) < 2)
                {
                    app_name_message = appNameSynonyms.get(0);
                    break;
                }
            }
        }

        message = PopupActivity.user_input;
        wordsOfTheMessage = new ArrayList<String>(Arrays.asList(message.split(" ")));
        for(String word:ignore)
        {
            numberOfWordsInTheMessage = wordsOfTheMessage.size();

            //loop to remove shitty words
            for(int wordIndex=0;wordIndex< numberOfWordsInTheMessage;wordIndex++)
            {
                if(wordsOfTheMessage.get(wordIndex).equalsIgnoreCase(word))
                {
                    wordsOfTheMessage.remove(wordIndex);
                    numberOfWordsInTheMessage = wordsOfTheMessage.size();
                }
            }
        }

        numberOfWordsInTheMessage = wordsOfTheMessage.size();
        outer:{
            for(int i=0;i< numberOfWordsInTheMessage;i++){
                for(String str:keyword){
                    if(wordsOfTheMessage.get(i).equalsIgnoreCase(str)){
                        iHaveTheSolution =true;
                        key_selected=str;
                        Log.i("Key Selected",key_selected);
                        break outer;
                    }
                }
                ignore.add(wordsOfTheMessage.get(i));
            }
        }

        if(iHaveTheSolution)
        {
            iHaveTheSolution =false;
            numberOfWordsInTheMessage = wordsOfTheMessage.size();

            for(String str:task)
            {
                for(int i=0;i< numberOfWordsInTheMessage;i++)
                {
                    if(wordsOfTheMessage.get(i).equalsIgnoreCase(str))
                    {
                        iHaveTheSolution =true;
                        tasksInUsersQuery.add(str);
                    }
                }
            }
            wordsOfTheMessage.clear();
        }

        if(!iHaveTheSolution)
        {
            Log.i("GOOGLE","Please google this");
        }

        else
        {
            Collections.sort(tasksInUsersQuery);
            numberOfTasksInUsersQuery = tasksInUsersQuery.size();
            for(int i=0;i< numberOfTasksInUsersQuery;i++)
                spaceSeparatedTasksInUsersQuery.append(tasksInUsersQuery.get(i)+" ");
            tasksInUsersQuery.clear();

            task_selected = spaceSeparatedTasksInUsersQuery.toString().trim();
            Log.i("TASK SELECTED", task_selected);
            spaceSeparatedTasksInUsersQuery.setLength(0);
        }
    }

    public static String use_json_query(Context mcontext,String query)
    {
        loginDataBaseAdapter = new LoginDataBaseAdapter(mcontext);
        try
        {
            loginDataBaseAdapter.open_hack();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        int sol = loginDataBaseAdapter.getAll(app_name_message, key_selected, task_selected);
        task_selected = "";
        String result = GetAllEntries.getSolution(sol);

        return result;

    }

    public static String user_solution(Context ctxt)
    {
        loginDataBaseAdapter = new LoginDataBaseAdapter(ctxt);
        try
        {
            loginDataBaseAdapter.open_hack();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        if(app_name_message == null || key_selected == null || task_selected == null)
            return "qwertyuiopasdfghjklzxcvbnm";
        int sol = loginDataBaseAdapter.getAll(app_name_message, key_selected, task_selected);
        task_selected = "";
        String result = GetAllEntries.getSolution(sol);

        return result;

    }

    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static int computeLevenshteinDistance(CharSequence lhs, CharSequence rhs) {
        int[][] distance = new int[lhs.length() + 1][rhs.length() + 1];

        for (int i = 0; i <= lhs.length(); i++)
            distance[i][0] = i;
        for (int j = 1; j <= rhs.length(); j++)
            distance[0][j] = j;

        for (int i = 1; i <= lhs.length(); i++)
            for (int j = 1; j <= rhs.length(); j++)
                distance[i][j] = minimum(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1] + ((lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1));

        return distance[lhs.length()][rhs.length()];
    }
}