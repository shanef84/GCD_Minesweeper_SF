package com.example.forbess.gcd_minesweeper_sf;

/**
 * Created by forbess on 03/04/2016.
 */
// imports
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CustomView extends View{
    //Constants
    private final int boardsize = 10;
    private int cellWidth, cellHeight;
    private Paint grey, white, red;

    //Grid
    private int[][] grid;
    private boolean[][] cells;
    //Global flag for disabling the touch if mine is hit
    private boolean touch;

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

    //override the onMeasure() method to make CustomView a square
    @Override
    protected void onMeasure(int widthMeasure, int heightMeasure) {
        super.onMeasure(widthMeasure, heightMeasure);
        int size =0;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int widthPadding = width - getPaddingLeft() - getPaddingRight();
        int heightPadding = height - getPaddingTop() - getPaddingBottom();
        //set dimensions
        if (widthPadding > heightPadding) {
            size = heightPadding;
        } else {
            size = widthPadding;
        }
        setMeasuredDimension(size + getPaddingLeft() + getPaddingRight(), size + getPaddingTop() + getPaddingBottom());
    }

    // refactored init method as most of this code is shared by all the constructors
    private void init() {
        //set colors
        white = new Paint(Paint.ANTI_ALIAS_FLAG);
        grey = new Paint(Paint.ANTI_ALIAS_FLAG);
        red = new Paint(Paint.ANTI_ALIAS_FLAG);
        white.setColor(Color.WHITE);white.setStrokeWidth(5);
        grey.setColor(Color.GRAY);
        red.setColor(Color.RED);
        //set cell sizes
        cellWidth = (int) getMeasuredWidth() / boardsize;
        cellHeight = (int) getMeasuredHeight() / boardsize;
        //grid
        cells = new boolean[boardsize][boardsize];
        for (int i=0; i<boardsize; i++){
            for (int j=0; j<boardsize; j++){
                cells[i][j]=false;
            }
        }
    }

    // public method that needs to be overridden to draw the contents of this widget
    public void onDraw(Canvas canvas) {
        // call the superclass method
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        for (int i = 1; i < boardsize; i++) {
            canvas.drawLine(0, i*(getHeight()/boardsize), getWidth(), i*(getHeight()/boardsize), white);
            canvas.drawLine(i*(getWidth()/boardsize), 0, i*(getWidth()/boardsize), getHeight(), white);
        }
        /*for (int i = 1; i < boardsize; i++) {
            canvas.drawLine(0, i * cellHeight, getWidth(), i * cellHeight, white);
        }*/
    }

    // public method that needs to be overridden to handle the touches from a user
    public boolean onTouchEvent(MotionEvent event) {
        // if we get to this point they we have not handled the touch
        // ask the system to handle it instead
        return super.onTouchEvent(event);
    }

}
