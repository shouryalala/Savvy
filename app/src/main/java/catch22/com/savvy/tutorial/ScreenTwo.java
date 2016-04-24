package catch22.com.savvy.tutorial;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import catch22.com.savvy.R;

public class ScreenTwo extends TutorialActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        instructions.setText("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?");
//
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
//                            TutorialActivity.navigate(ScreenTwo.this, ScreenOne.class);
//                        } else
//                        {
//                            overridePendingTransition(R.animator.animation_slide_leave, R.animator.animation_slide);
//                            TutorialActivity.navigate(ScreenTwo.this, ScreenThree.class);
//                        }
//                    }
//                }
//            }
//        }).start();
    }
}
