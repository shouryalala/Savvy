package catch22.com.savvy.DBEssentials;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by root on 24/4/16.
 */
public class LoginDataBaseAdapter
{
    static final String DATABASE_NAME = "AngelHack24.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    int i =0;

    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"HACK"+
            "( " +"ID"+" integer primary key autoincrement,"+
            "APPNAME text,KEYWORD text,TASK text,LINK integer);";
    // Variable to hold the database instance


    public SQLiteDatabase db;

    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;


    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public  LoginDataBaseAdapter open_hack() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        db.close();

    }

    public SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String app_name,String keyword,String task,int link)
    {

        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("APPNAME",app_name);
        newValues.put("KEYWORD",keyword);
        newValues.put("TASK",task);
        newValues.put("LINK",link);

        // Insert the row into your table
        db.insert("HACK", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public int getAll(String appname,String key, String task)
    {
        String selection="APPNAME=? AND KEYWORD = ? AND TASK = ? ";
        //SELECT LINK FROM HACK WHERE APPNAME=? AND KEYWORD = ? AND TASK = ?
        String[] args= {appname,key,task};
        Cursor cursor=db.query("HACK",new String[]{"LINK"},selection,args,null,null,null );
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return -1;
        }
        cursor.moveToFirst();
        int link= cursor.getInt(cursor.getColumnIndex("LINK"));
        cursor.close();
        return link;
    }

    public boolean exists()
    {
        String appname = "whatsapp";
        Cursor cursor=db.query("HACK",null,"APPNAME=?",new String[]{appname},null,null,null );
        if(cursor.getCount()>0)return true;
        return false;

    }
}
