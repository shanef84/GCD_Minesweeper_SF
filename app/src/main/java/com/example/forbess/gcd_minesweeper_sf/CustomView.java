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

import java.util.Random;

public class CustomView extends View{
    //private fields for rendering the view
    private final int boardsize = 10;
    private int cellWidth, cellHeight;
    private Paint grey, white, red, mineText, textOne, textTwo, textThree, textFour;
    private float textSize, textWidth;
    private String mine, one, two, three, four;
    private boolean touch = false;
    //Grid
    private int[][] board;       //record value in cells 0 = no touch
    private boolean[][] cells;  //record touches on cell

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
    //override the onSizeChanged method
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setSize();
    }
    //override the onMeasure() method
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

    private void setSize(){
        //set cell sizes
        cellWidth = getWidth() / boardsize;
        cellHeight = getHeight() / boardsize;
        cells = new boolean[boardsize][boardsize];
    }

    // refactored init method as most of this code is shared by all the constructors
    private void init() {
        //set colors
        white = new Paint(Paint.ANTI_ALIAS_FLAG); white.setColor(Color.WHITE);white.setStrokeWidth(2);
        grey = new Paint(Paint.ANTI_ALIAS_FLAG);grey.setColor(Color.GRAY);
        //mine text+size
        mine = "M";one="1";two="2";three="3";four="4";
        red = new Paint(Paint.ANTI_ALIAS_FLAG);red.setColor(Color.RED);
        mineText = new Paint(Paint.ANTI_ALIAS_FLAG);textOne = new Paint(Paint.ANTI_ALIAS_FLAG);
        textTwo = new Paint(Paint.ANTI_ALIAS_FLAG);textThree = new Paint(Paint.ANTI_ALIAS_FLAG);
        textFour = new Paint(Paint.ANTI_ALIAS_FLAG);
        mineText.setColor(Color.BLACK);textOne.setColor(Color.BLUE);
        textTwo.setColor(Color.GREEN);textThree.setColor(Color.YELLOW);textFour.setColor(Color.RED);
        mineText.setTextSize(60);textOne.setTextSize(60);textTwo.setTextSize(60);textThree.setTextSize(60);textFour.setTextSize(60);
        mineText.setTextAlign(Paint.Align.CENTER);textOne.setTextAlign(Paint.Align.CENTER);textTwo.setTextAlign(Paint.Align.CENTER);
        textWidth = mineText.measureText(mine); textSize = mineText.getTextSize();


        board = new int [boardsize][boardsize];
        for (int i = 0; i < boardsize; i++) {
            for (int j = 0; j < boardsize; j++) {
                board[i][j] = 0;
            }
        }
        setMines();
        setNumbers();
    }

    // public method that needs to be overridden to draw the contents of this widget
    public void onDraw(Canvas canvas) {
        // call the superclass method
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        for (int i = 0; i < boardsize; i++) {
            for (int j = 0; j < boardsize; j++) {
                if (cells[i][j]) {
                    if (board[i][j]==1) {
                        canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, grey);
                        //print numbers 1
                        canvas.drawText(one, (i * cellWidth)+ textWidth, (j * cellHeight) + textSize, textOne);
                    }
                    else if (board[i][j]==2){
                        canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, grey);
                        //print numbers 2
                        canvas.drawText(two, (i * cellWidth)+ textWidth, (j * cellHeight) + textSize, textTwo);
                    }
                    else if (board[i][j]==3){
                        canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, grey);
                        //print numbers 3
                        canvas.drawText(three, (i * cellWidth)+ textWidth, (j * cellHeight) + textSize, textThree);
                    }
                    else if (board[i][j]==4){
                        canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, grey);
                        //print numbers 4
                        canvas.drawText(four, (i * cellWidth)+ textWidth, (j * cellHeight) + textSize, textFour);
                    }
                    else if (board[i][j]==5){
                        canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, red);
                        canvas.drawText(mine, (i * cellWidth)+ textWidth, (j * cellHeight) + textSize, mineText);
                        //game over
                        touch = true;
                        Toast.makeText(getContext(), "GAME OVER!", Toast.LENGTH_SHORT).show();

                    }
                    else { //plain grey
                        canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, grey);
                    }
                }
            }
        }
        //draw lines
        for (int i = 1; i < boardsize; i++) {
            canvas.drawLine(0, i*(getHeight()/boardsize), getWidth(), i*(getHeight()/boardsize), white);
            canvas.drawLine(i*(getWidth() / boardsize), 0, i*(getWidth()/boardsize), getHeight(), white);
        }
    }

    public void setMines(){
        Random r = new Random();
        int mineX, mineY;
        int i = 0;
        while (i<20){
            mineX = r.nextInt(10);
            mineY = r.nextInt(10);
            if (board[mineX][mineY]!=5) {
                board[mineX][mineY] = 5;
                i++;
            }
        }


    }
    public void setNumbers(){
        for (int i = 0; i < boardsize; i++){
            for ( int j = 0; j < boardsize; j++){
                if (board[i][j]==5){
                    //render numbers
                    if ((i>0 && i<9) && (j>0 && j<9)) {
                        if (board[i - 1][j - 1] < 4) board[i - 1][j - 1] += 1;  //0,0
                        if (board[i - 1][j] < 4) board[i - 1][j] += 1;      //0,1
                        if (board[i - 1][j + 1] < 4) board[i - 1][j + 1] += 1;  //0,2
                        if (board[i][j - 1] < 4) board[i][j - 1] += 1;      //1,0
                        if (board[i][j + 1] < 4) board[i][j + 1] += 1;      //1,2
                        if (board[i + 1][j - 1] < 4) board[i + 1][j - 1] += 1;  //2,0
                        if (board[i + 1][j] < 4) board[i + 1][j] += 1;      //2,1
                        if (board[i + 1][j + 1] < 4) board[i + 1][j + 1] += 1;  //2,2
                    }
                    if ((i==0) && (j>0 && j<9)){
                        if (board[i][j - 1] < 4) board[i][j - 1] += 1;      //1,0
                        if (board[i][j + 1] < 4) board[i][j + 1] += 1;      //1,2
                        if (board[i + 1][j - 1] < 4) board[i + 1][j - 1] += 1;  //2,0
                        if (board[i + 1][j] < 4) board[i + 1][j] += 1;      //2,1
                        if (board[i + 1][j + 1] < 4) board[i + 1][j + 1] += 1;  //2,2
                    }
                    if ((j==0) && (i>0 && i<9)){
                        if (board[i - 1][j] < 4) board[i - 1][j] += 1;      //0,1
                        if (board[i - 1][j + 1] < 4) board[i - 1][j + 1] += 1;  //0,2
                        if (board[i][j + 1] < 4) board[i][j + 1] += 1;      //1,2
                        if (board[i + 1][j] < 4) board[i + 1][j] += 1;      //2,1
                        if (board[i + 1][j + 1] < 4) board[i + 1][j + 1] += 1;  //2,2
                    }
                    if ((i==9) && (j>0 && j<9)){
                        if (board[i - 1][j - 1] < 4) board[i - 1][j - 1] += 1;  //0,0
                        if (board[i - 1][j] < 4) board[i - 1][j] += 1;      //0,1
                        if (board[i - 1][j + 1] < 4) board[i - 1][j + 1] += 1;  //0,2
                        if (board[i][j - 1] < 4) board[i][j - 1] += 1;      //1,0
                        if (board[i][j + 1] < 4) board[i][j + 1] += 1;      //1,2
                    }
                    if ((j==9) && (i>0 && i<9)){
                        if (board[i - 1][j - 1] < 4) board[i - 1][j - 1] += 1;  //0,0
                        if (board[i - 1][j] < 4) board[i - 1][j] += 1;      //0,1
                        if (board[i][j - 1] < 4) board[i][j - 1] += 1;      //1,0
                        if (board[i + 1][j - 1] < 4) board[i + 1][j - 1] += 1;  //2,0
                        if (board[i + 1][j] < 4) board[i + 1][j] += 1;      //2,1
                    }
                    if (i==0 && j==0){
                        if (board[i][j + 1] < 4) board[i][j + 1] += 1;      //1,2
                        if (board[i + 1][j + 1] < 4) board[i + 1][j + 1] += 1;  //2,2
                        if (board[i + 1][j] < 4) board[i + 1][j] += 1;      //2,1
                    }
                    if (i==0 && j==9){
                        if (board[i][j - 1] < 4) board[i][j - 1] += 1;      //1,0
                        if (board[i + 1][j - 1] < 4) board[i + 1][j - 1] += 1;  //2,0
                        if (board[i + 1][j] < 4) board[i + 1][j] += 1;      //2,1
                    }
                    if (i==9 && j==0){
                        if (board[i - 1][j] < 4) board[i - 1][j] += 1;      //0,1
                        if (board[i - 1][j + 1] < 4) board[i - 1][j + 1] += 1;  //0,2
                        if (board[i][j + 1] < 4) board[i][j + 1] += 1;      //1,2
                    }
                    if (i==9 && j==9){
                        if (board[i - 1][j - 1] < 4) board[i - 1][j - 1] += 1;  //0,0
                        if (board[i - 1][j] < 4) board[i - 1][j] += 1;      //0,1
                        if (board[i][j - 1] < 4) board[i][j - 1] += 1;      //1,0
                    }
                }
            }
        }
    }

    // public method that needs to be overridden to handle the touches from a user
    public boolean onTouchEvent(MotionEvent event) {
        //this indicates that the user has placed a finger on the screen
        if (event.getAction() == MotionEvent.ACTION_DOWN && !touch) {
            int x = (int)(event.getX() / cellWidth);
            int y = (int)(event.getY() / cellHeight);
            //If cell is touched, and not a mine there, make it not clickable (will not change color) for next time
            if (!cells[x][y]){
                cells[x][y] = true;
            }
            invalidate();

        }
        return true;
        }

    public void reset(){
        touch=false;
        for (int i = 0; i < boardsize; i++) {
            for (int j = 0; j < boardsize; j++) {
                cells[i][j]=false;
                board[i][j] = 0;
            }
        }
        setMines();
        setNumbers();
        invalidate();
    }

}
