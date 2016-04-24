package catch22.com.savvy;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Handler;

import catch22.com.savvy.tutorial.ScreenOne;
import files.FileHandler;

public class SplashScreen extends Activity
{

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreen.this, FileHandler.exists(SplashScreen.this) ? SettingsActivity.class : ScreenOne.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }

        }, SPLASH_DISPLAY_LENGTH);
    }
}