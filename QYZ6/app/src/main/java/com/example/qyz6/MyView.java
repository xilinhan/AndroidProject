package com.example.qyz6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author xilinhan
 * @description:
 * @date :2022/3/30 22:45
 */
public class MyView extends View {

    Paint mPaint;

    Rect mRect;


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mRect = new Rect();

        //mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        //mPaint.setStyle(Paint.Style.FILL);
        mRect.top = 0;
        mRect.bottom = 100;
        mRect.left = 0;
        mRect.right = 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawRect(mRect, mPaint);
        canvas.drawCircle(300, 300, 50 ,mPaint);

    }
}
