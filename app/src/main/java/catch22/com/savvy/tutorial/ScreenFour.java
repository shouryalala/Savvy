package catch22.com.savvy.tutorial;

import android.os.Bundle;

import files.FileHandler;
import catch22.com.savvy.SettingsActivity;

public class ScreenFour extends TutorialActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        instructions.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        nextButton.setText("Got it");

        FileHandler.write(this);

        setPrevious(ScreenFour.this, ScreenThree.class);
        setNext(ScreenFour.this, SettingsActivity.class);

//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                while(true)
//                {
//                    if(fling)
//                    {
//                        Log.d("Fling", "is true");
//                        fling = false;
//                        if(velocityX > 0)
//                        {
//                            overridePendingTransition(R.animator.animation_slide, R.animator.animation_slide_leave);
//                            TutorialActivity.navigate(ScreenFour.this, ScreenThree.class);
//                        } else
//                        {
//                            overridePendingTransition(R.animator.animation_slide_leave, R.animator.animation_slide);
//                            TutorialActivity.navigate(ScreenFour.this, SettingsActivity.class);
//                        }
//                    }
//                }
//            }
//        }).start();
    }
}
