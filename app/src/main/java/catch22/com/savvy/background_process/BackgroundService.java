package catch22.com.savvy.background_process;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import catch22.com.savvy.R;
import catch22.com.savvy.popup.PopupActivity;

public class BackgroundService extends Service
{
    private NotificationManager mNM;
    public static String recent="";
    Intent mintent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {return null;}

    @Override
    public void onCreate() {
        super.onCreate();
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mintent = new Intent(this, PopupActivity.class);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                    while (true)
                    {
                        String temporaryName = getUsageStatsForegroundActivityName();
                        if(!temporaryName.equals(recent) && !temporaryName.contains("savvy"))
                            recent = temporaryName;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            }
        }).start();

        showNotification();


        return START_STICKY;

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private String getUsageStatsForegroundActivityName() {

        String topActivity = null;
        try{
            UsageStatsManager mUsageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
            long endTime = System.currentTimeMillis();
            long beginTime = endTime - 1000 * 60;

            // result

            // We get usage stats for the last minute
            List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, beginTime, endTime);

            // Sort the stats by the last time used
            if (stats != null) {
                SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
                for (UsageStats usageStats : stats) {
                    mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (!mySortedMap.isEmpty()) {//mySortedMap != null &&
                    topActivity = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if (topActivity != null){
            //Log.i("MUCH AWAITED->",topActivity);
            return topActivity;}
        else
            return ""+-1;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNM.cancelAll();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void showNotification() {

        // The PendingIntent to launch our activity if the user selects this notification

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                mintent, 0);

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)  // the status icon
                .setTicker("Service Running")  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle(("Savvy"))  // the label of the entry
                .setContentText("What can I help you with?")  // the contents of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .setVisibility(-1)
                .setOngoing(true)
                .build();

        // Send the notification.
        mNM.notify(5, notification);
    }
}