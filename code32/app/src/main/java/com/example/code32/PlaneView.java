package com.example.code32;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;


import java.util.Timer;
import java.util.TimerTask;

/**
 * @author xilinhan
 * @description:
 * @date :2022/6/12 13:39
 */
public class PlaneView extends View {

    float currentX;
    float currentY;

    private Paint mPaint = new Paint();
    private Bitmap mBitmap;
    private Bitmap mBitmap1;
    private int index;



    public PlaneView(Context context) {
        super(context);
        init(context);
    }

    public PlaneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlaneView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        //定义飞机图片
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_foreground);
        mBitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_background);
        //启动定时器来回切换飞机图片，实现动画效果
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                index++;
                PlaneView.this.invalidate();
            }
        },0, 100L);
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(index % 2 == 0 ? mBitmap : mBitmap1, currentX, currentY, mPaint);
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                performClick();
                return true;
        }
        return false;
    }
}
