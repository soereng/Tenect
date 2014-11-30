package de.tenect.tum.test;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements SocialChooserFragment.NoticeDialogListener {

    private int _xDelta;
    private int _yDelta;
    ImageView circle_fb;

    private static final int SOCIAL_FACEBOOK = 0;
    private static final int SOCIAL_TWITTER = 1;
    private static final int SOCIAL_LINKEDIN = 2;

    SocialContainer socialFB;


    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureDetector = new GestureDetector(this, new SingleTapConfirm());

        circle_fb = (ImageView)findViewById(R.id.circle_wrapper);
        socialFB = new SocialContainer(0,0, true, circle_fb);
        socialFB.circle.setOnTouchListener(t);

    }



    View.OnTouchListener t = new View.OnTouchListener(){

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (gestureDetector.onTouchEvent(event)) {

                //open dialog
                DialogFragment newFragment = new SocialChooserFragment();
                newFragment.show(getFragmentManager(), "profile_dialog");

                return true;
            } else {

                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:

                        _xDelta = (int) (X - circle_fb.getTranslationX());
                        _yDelta = (int) (Y - circle_fb.getTranslationY());


                        break;
                    case MotionEvent.ACTION_UP:
                        //TODO: add animation
                        //Reset position to center of screen
                        circle_fb.setTranslationX(0);
                        circle_fb.setTranslationY(0);
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:

                        circle_fb.setTranslationX(X - _xDelta);
                        circle_fb.setTranslationY(Y - _yDelta);
                        break;
                }

                return true;
            }
            }
    };

    @Override
    public void onDialogClick(int profileId) {
        if(profileId == SOCIAL_FACEBOOK){
            circle_fb.setImageResource(R.drawable.circle_fb);
            //TODO: store in shared preferences
        } else if(profileId == SOCIAL_TWITTER) {
            circle_fb.setImageResource(R.drawable.circle_twitter);
        } else if (profileId == SOCIAL_LINKEDIN){
            circle_fb.setImageResource(R.drawable.circle_linkedin);
        }
    }



    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
