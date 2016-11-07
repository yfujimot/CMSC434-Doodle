package com.thinkfuji.cmsc434_doodle;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
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
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, 0, getWidth(), getHeight(), mPaint);
    }
}