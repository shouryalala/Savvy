package files;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import catch22.com.savvy.SettingsActivity;
import catch22.com.savvy.popup.PopupActivity;

public class FileHandler
{
    private static final String fileName = "rnNJhsSx.bin";

    public static void read(Context context)
    {
        FileInputStream inputStream;

        try
        {
            Log.d("File", "Trying to read file");
            inputStream = context.openFileInput(fileName);

            //sequence of events
            //read the enable notification boolean
            //read the service boolean
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            SettingsActivity.notificationBarEnabled = (boolean) objectInputStream.readObject();
            SettingsActivity.internetServiceEnabled = (boolean) objectInputStream.readObject();
            PopupActivity.string = (String) objectInputStream.readObject();
            Log.d("File Read", SettingsActivity.notificationBarEnabled + " " + SettingsActivity.internetServiceEnabled);
            Log.d("File", "Read");

        } catch (Exception fileNotFound)
        {

        }
    }

    public static void remove(Context context)
    {
        try
        {
            Log.d("File", "Trying to remove file");
            File file = new File(context.getFilesDir(), fileName);
            file.delete();
            Log.d("File", "Deleted");
        } catch (Exception fileDoesNotExist)
        {

        }
    }

    public static boolean exists(Context context)
    {
        File f = new File(context.getFilesDir(), fileName);

        return f.exists();
    }

    public static void write(Context context)
    {
        FileOutputStream outputStream;

        remove(context);

        try
        {
            Log.d("File", "Trying to write file");
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(SettingsActivity.notificationBarEnabled);
            objectOutputStream.writeObject(SettingsActivity.internetServiceEnabled);
            objectOutputStream.writeObject(PopupActivity.string);
            Log.d("File Written", SettingsActivity.notificationBarEnabled + " " + SettingsActivity.internetServiceEnabled);
            outputStream.close();
            Log.d("File", "Written");
        } catch (Exception fileDoesNotExist)
        {
            fileDoesNotExist.printStackTrace();
        }
    }
}