package com.example.justinchong.androidchess.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.io.Serializable;

/**
 * Created by justinchong on 4/30/17.
 */

public class Square implements Serializable{

    private int row;
    private int column;
    public boolean selection;

    public Piece piece;

    public Canvas canvas;
    private Paint original;
    private Paint highlight;
    public Rect rectangle;

    public Square(int row, int col) {
        this.row = row;
        this.column = col;

        this.original = new Paint();
        this.highlight = new Paint();
        highlight.setColor(Color.parseColor("#BC8F8F"));

        if ((column + row) % 2 == 0) {
            original.setColor(Color.parseColor("#6B4423"));
        }
        else
            original.setColor(Color.parseColor("#F0DC82"));
    }

    public Square(int row, int col, Piece piece) {
        this.row = row;
        this.column = col;
        this.piece = piece;


        this.original = new Paint();
        this.highlight = new Paint();
        highlight.setColor(Color.parseColor("#BC8F8F"));

        if ((column + row) % 2 == 0) {
            original.setColor(Color.parseColor("#6B4423"));
        }
        else
            original.setColor(Color.parseColor("#F0DC82"));
    }

    public Piece getPiece(){
        return this.piece;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public void setRectangle(Rect newRect){
        this.rectangle = newRect;
    }

    public Rect getRectangle(){
        return this.rectangle;
    }

    public void drawSquare(Canvas canvas) {
        this.canvas = canvas;
        if (selection)
            canvas.drawRect(rectangle, highlight);
        else
            this.rectangle = rectangle;
            canvas.drawRect(getRectangle(), original);
    }

    public void drawSquare( Canvas canvas, Rect sqr, Bitmap icon) {
        this.canvas = canvas;
        if (selection) {
            canvas.drawRect(rectangle, highlight);
        }
        else {
            canvas.drawRect(rectangle, original);
        }

        canvas.drawBitmap(icon,rectangle.left,rectangle.top, new Paint(Color.RED));

    }

    public boolean touch(int x, int y) {
        return rectangle.contains(x, y);
    }
}


