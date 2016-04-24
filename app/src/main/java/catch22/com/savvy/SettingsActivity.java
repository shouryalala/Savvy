package catch22.com.savvy;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import catch22.com.savvy.DBEssentials.GetAllEntries;
import catch22.com.savvy.background_process.BackgroundService;
import catch22.com.savvy.background_process.Constant;
import catch22.com.savvy.tutorial.ScreenOne;
import catch22.com.savvy.tutorial.TutorialActivity;
import files.FileHandler;

public class SettingsActivity extends AppCompatActivity
{
    public static boolean notificationBarEnabled = true;
    public static boolean internetServiceEnabled = false;

    private Switch notificationsSwitch;
    private Switch accessInternetSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        GetAllEntries.insertData(this);



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            if(!hasPermission()){
                startActivityForResult(
                        new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS),
                        Constant.MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS);
            }
        }

        FileHandler.read(this);

        notificationsSwitch = (Switch) findViewById(R.id.notificationSwitch);
        accessInternetSwitch = (Switch) findViewById(R.id.accessInternetSwitch);

        notificationsSwitch.setChecked(notificationBarEnabled);
        accessInternetSwitch.setChecked(internetServiceEnabled);

        notificationsSwitch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean value = notificationsSwitch.isActivated();
                Log.d("Settings", "Notifications " + (value ? "Enabled" : "Disabled"));
                FileHandler.write(SettingsActivity.this);
            }
        });

        accessInternetSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean value = accessInternetSwitch.isActivated();
                Log.d("Settings", "Notifications " + (value ? "Enabled" : "Disabled"));
                FileHandler.write(SettingsActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);

        return true;
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS){
            if (!hasPermission()){
                startActivityForResult(
                        new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS),
                        Constant.MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS);

            }
        }
    }

    private boolean hasPermission() {
        AppOpsManager appOps = (AppOpsManager)
                getSystemService(Context.APP_OPS_SERVICE);
        int mode = 0;
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.KITKAT) {
            mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                    android.os.Process.myUid(), getPackageName());
        }
        return mode == AppOpsManager.MODE_ALLOWED;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private String getUsageStatsForegroundActivityName()
    {
        UsageStatsManager mUsageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        long endTime = System.currentTimeMillis();
        long beginTime = endTime - 1000 * 60;

        // result
        String topActivity = null;

        // We get usage stats for the last minute
        List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, beginTime, endTime);

        // Sort the stats by the last time used
        if (stats != null) {
            SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
            for (UsageStats usageStats : stats) {
                mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
            }
            if (mySortedMap != null && !mySortedMap.isEmpty()) {
                topActivity = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                Log.i("String Activity", "topActivity: " + topActivity);
            }
        }
        if (topActivity != null)
            return topActivity;
        else
            return Constant.ACTIVITY_NOT_FOUND;
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
            TutorialActivity.navigate(this, ScreenOne.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStop()
    {
        super.onStop();
        startService(new Intent(SettingsActivity.this, BackgroundService.class));
    }
}