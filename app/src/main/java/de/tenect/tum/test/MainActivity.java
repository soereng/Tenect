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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView circle = (ImageView)findViewById(R.id.circle);
        circle.setOnClickListener(l);
        circle.setOnTouchListener(t);

    }


    //RelativeLayout.LayoutParams parms;
    //LinearLayout.LayoutParams par;
    //float  x=0, y=0, dy=0, dx=0;

    private int _xDelta;
    private int _yDelta;

    View.OnTouchListener t = new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            ImageView j = (ImageView)findViewById(R.id.circle);
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                    _xDelta = (int) (X - j.getTranslationX());
                    _yDelta = (int) (Y - j.getTranslationY());
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();

                    j.setTranslationX(X - _xDelta);
                    j.setTranslationY(Y - _yDelta);
                    break;
            }

            return true;
            }
    };

    View.OnClickListener l = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.circle){
                Toast.makeText(getApplicationContext(), "Circle clicked", Toast.LENGTH_SHORT).show();
            }
        }
    };


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
