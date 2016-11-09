package com.thinkfuji.cmsc434_doodle;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Yoshi on 11/7/16.
 */

public class Canvas extends View {
    private Path mPath = new Path();
    private Paint mPaint = new Paint();

    public Canvas(Context context) {
        super(context);
        init(null, 0);
    }

    public Canvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public Canvas(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        init(attrs, defStyle);
    }

    public void init(AttributeSet attrs, int defStyle) {
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }


    public void clearCanvas() {
        Log.i("Canvas", "Clear canvas button pressed");
        this.mPath.reset();
        invalidate();
    }

    public void setBrushColor(int color) {
        this.mPaint.setColor(color);
    }

    public Path getPaths() {
        return this.mPath;
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        invalidate();

        return true;
    }

    public void undoPath() {
        this.mPath.rewind();
    }
}
