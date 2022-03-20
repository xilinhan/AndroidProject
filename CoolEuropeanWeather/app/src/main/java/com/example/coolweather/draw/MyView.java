package com.example.coolweather.draw;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author xilinhan
 * @description:
 * @date :2022/3/17 18:25
 */
public class MyView extends View {

    private Resources mResources;
    private Paint mBitPaint;
    private Bitmap mBitmap;
    //如果要将bitmap绘制在屏幕上，则需要两个Rect，第一个确定绘制得bitmap区域，第二个表示将bitmap绘制在屏幕的什么地方
    //mSrcRect 取值为整个Bitmap 区域 ，mDestRect 取值为view左上方和bitmap同样大小
    private Rect mSrcRect, mDestRect;

    //定义View得宽高
    private int mTotalWidth, mTotalHeight;

    public MyView(Context context) {
        super(context);
        mResources = getResources();
        initBitmap();
        initPaint();
        mSrcRect = new Rect(0, 0, mTotalWidth, mTotalHeight);
        mDestRect = new Rect(0, 0, mTotalWidth, mTotalHeight);

    }

    private void initPaint() {
        //Paint.ANTI_ALIAS_FLAG 抗锯齿,setFilterBitmap滤波,setDither防抖动，这三个是增强图像清晰、柔和得设置
        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaint.setFilterBitmap(true);
        mBitPaint.setDither(true);
    }

    private void initBitmap() {
        mBitmap = ((BitmapDrawable) mResources.getDrawable(0)).getBitmap();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalHeight = h;
        mTotalWidth = w;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, mSrcRect, mDestRect, mBitPaint);
    }
}
