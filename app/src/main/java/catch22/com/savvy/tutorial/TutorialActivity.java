package catch22.com.savvy.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import catch22.com.savvy.R;

public abstract class TutorialActivity extends AppCompatActivity
{
    protected Button nextButton, backButton;
    protected ImageView image;
    protected TextView instructions;

    protected float velocityX;

    private GestureDetector gestureDetector;

    protected boolean fling;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sai);

        nextButton = (Button) findViewById(R.id.next_button);
        backButton = (Button) findViewById(R.id.back_button);

        image = (ImageView) findViewById(R.id.imageView);

        instructions = (TextView) findViewById(R.id.textView);

        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener()
        {
            @Override
            public boolean onDown(MotionEvent e)
            {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e)
            {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e)
            {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
            {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e)
            {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
            {
//                TutorialActivity.this.velocityX = velocityX;
//                fling = true;

                if(velocityX < 0)
                    nextButton.performClick();
                else if(velocityY > 0)
                    backButton.performClick();
                Log.d("Gesture", "Fling Detected");
                return true;
            }
        });
    }

    public static void navigate(final Activity source, final Class<?> destination)
    {
        Intent nextIntent = new Intent(source, destination);
        source.startActivity(nextIntent);
        source.finish();
    }

    private void setNavigation(Button button, final AppCompatActivity source, final Class<?> destination)
    {
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navigate(source, destination);
            }
        });
    }

    protected void setNext(final AppCompatActivity source, final Class<?> type)
    {
        setNavigation(nextButton, source, type);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    protected void setPrevious(final AppCompatActivity source, final Class<?> type)
    {
        setNavigation(backButton, source, type);
    }
}