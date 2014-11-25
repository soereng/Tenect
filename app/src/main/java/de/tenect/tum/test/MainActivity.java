package de.tenect.tum.test;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.*;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.*;

public class MainActivity extends Activity {

    private int _xDelta;
    private int _yDelta;
    ImageView circle;

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureDetector = new GestureDetector(this, new SingleTapConfirm());

        circle = (ImageView)findViewById(R.id.circle);
        circle.setOnTouchListener(t);

    }


    View.OnTouchListener t = new View.OnTouchListener(){

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (gestureDetector.onTouchEvent(event)) {
                if(v.getId() == R.id.circle){
                    Toast.makeText(getApplicationContext(), "Circle clicked", Toast.LENGTH_SHORT).show();
                    //TODO: Circle got touched, add functionality for that case
                }
                return true;
            } else {
                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:

                        //Log.i("MAIN", "X: "+ X);
                        //Log.i("MAIN", "Y: "+ Y);

                        //oldX = X;
                        //oldY = Y;

                        //Log.i("MAIN", "translationX: "+ circle.getTranslationX());
                        //Log.i("MAIN", "translationY: "+ circle.getTranslationY());

                        _xDelta = (int) (X - circle.getTranslationX());
                        _yDelta = (int) (Y - circle.getTranslationY());

                        //Log.i("MAIN", "x: "+ _xDelta);
                        //Log.i("MAIN", "y: "+ _yDelta);

                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:

                        //int diffX = oldX - X;
                        //Log.i("MAIN", "action_move triggered: "+ diffX);

                        circle.setTranslationX(X - _xDelta);
                        circle.setTranslationY(Y - _yDelta);
                        break;
                }

                return true;
            }
            }
    };

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
