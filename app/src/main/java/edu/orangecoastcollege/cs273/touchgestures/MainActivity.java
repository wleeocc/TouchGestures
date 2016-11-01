package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private Button clearButton;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    // Define a GestureDetector to listen to events on the ScrollView
    private  GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);
        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        singleTapTextView = (TextView) findViewById(R.id.singleTapTextView);
        doubleTapTextView = (TextView) findViewById(R.id.doubleTapTextView);
        longPressTextView = (TextView) findViewById(R.id.longPressTextView);
        scrollTextView = (TextView) findViewById(R.id.scrollTextView);
        flingTextView = (TextView) findViewById(R.id.flingTextView);
        clearButton = (Button) findViewById(R.id.clearButton);

        // Hooking up the gesture detector to our scroll view
        // 4 out of 5 gestures handled
        gestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
        // Special case: double tap
        gestureDetector.setOnDoubleTapListener(this);

    }

    public void clearAll(View view){
        gesturesLogTextView.setText("");
        singleTapTextView.setText("0");
        doubleTapTextView.setText("0");
        longPressTextView.setText("0");
        scrollTextView.setText("0");
        flingTextView.setText("0");
        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings = 0;
    }


    // Any touch event is now dispatched from activity to the ScrollView
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        singleTaps++;
        // Let's append to our gesture log:
        gesturesLogTextView.append("\nonSingleTapConfirmed touch event");
        singleTapTextView.setText(String.valueOf(singleTaps));
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        // if second time
        doubleTaps++;
        doubleTapTextView.setText(String.valueOf(doubleTaps));
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDown touch event");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonShowPress touch event");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonSingleTapUp touch event");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        scrolls++;
        gesturesLogTextView.append("\nonScroll touch event");
        scrollTextView.setText(String.valueOf(scrolls));
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        longPresses++;
        gesturesLogTextView.append("\nonLongPress touch event");
        longPressTextView.setText(String.valueOf(longPresses));
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        flings++;
        gesturesLogTextView.append("\nonFling touch event");
        flingTextView.setText(String.valueOf(flings));
        return true;
    }
}
