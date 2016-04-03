package com.example.forbess.gcd_minesweeper_sf;

/**
 * Created by forbess on 03/04/2016.
 */
// imports
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View{

    //default constructor
    public CustomView(Context c){
        super(c);
        init();
    }

    //constructor that takes in a context + list of attributes set through XML
    public CustomView(Context c, AttributeSet as) {
        super(c, as);
        init();
    }

    public CustomView(Context c, AttributeSet as, int default_style) {
        super(c, as, default_style);
        init();
    }
    // refactored init method as most of this code is shared by all the constructors
    private void init() {
    }

    // public method that needs to be overridden to draw the contents of this widget
    public void onDraw(Canvas canvas) {
        // call the superclass method
        super.onDraw(canvas);
    }

    // public method that needs to be overridden to handle the touches from a user
    public boolean onTouchEvent(MotionEvent event) {
        // if we get to this point they we have not handled the touch
        // ask the system to handle it instead
        return super.onTouchEvent(event);
    }

}
