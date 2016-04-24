package catch22.com.savvy.tutorial;

import android.os.Bundle;
import android.view.View;

public class ScreenOne extends TutorialActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        instructions.setText("Savvy is an interactive mobile assistance app that provides an immersive experience to flatten the learning curve for technologically unsavvy people." +
                "Savvy acts as a personal assistant, available just two clicks away at any point of time, whether you are connected to the internet or not! Savvy provides answers to countless number of frequently asked questions that users have within an app, allowing them to work without too many hickups. " +
                "The thing that sets Savvy apart from other FAQ apps is that it provides answers on the go for any app, by recognizing the currently working application." +
                "Savvy is always there for you at every step, at every hurdle." );
        backButton.setVisibility(View.INVISIBLE);

        setNext(ScreenOne.this, ScreenTwo.class);
    }
}