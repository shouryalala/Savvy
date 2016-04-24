package catch22.com.savvy.tutorial;

import android.os.Bundle;

public class ScreenThree extends TutorialActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        instructions.setText("\"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.");

        setNext(ScreenThree.this, ScreenFour.class);
        setPrevious(ScreenThree.this, ScreenTwo.class);

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
//                            TutorialActivity.navigate(ScreenThree.this, ScreenTwo.class);
//                        } else
//                        {
//                            overridePendingTransition(R.animator.animation_slide_leave, R.animator.animation_slide);
//                            TutorialActivity.navigate(ScreenThree.this, ScreenFour.class);
//                        }
//                    }
//                }
//            }
//        }).start();
    }
}
