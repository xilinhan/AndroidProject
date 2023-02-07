package com.example.qyz7;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


/**
 * @author xilinhan
 * @description:
 * @date :2022/4/30 11:45
 */
public class WIFIView extends View {

    private Paint mPaint;
    private Handler mHandler;

    //WIFI控件宽高较小的一变的长度
    private int wifiLength;

    //开始角度
    private float startAngle = -135;

    //扇形或者弧形的旋转角度
    private float sweepAngle = 90;

    //信号大小，默认为4格
    private int singleSize = 4;

    //每次应该绘制的信号个数
    private float shouldExistSignalSize = 0f;

    public WIFIView(Context context) {
        super(context);
        init();
    }

    public WIFIView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WIFIView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /*
    * 初始化
    * 通过handler的postDelayed方法可以死循环不断执行view的invalidate()方法来达到动态绘制的效果(每次绘制需要控制绘制几个信号)
    * */
    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);

        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidate();
                mHandler.postDelayed(this, 500);
            }
        }, 500);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        wifiLength = Math.min(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shouldExistSignalSize++;
        if(shouldExistSignalSize > 4){
            shouldExistSignalSize = 1;
        }
        canvas.save();
        //计算最小的扇形信号所在的圆的半径
        float signalRadius = (float) (wifiLength / (2.0 * singleSize));
        //向下平移画布，保证绘制的图形能够完全显示
        canvas.translate(0, signalRadius);
        for (int i = 0; i < singleSize; i++) {
            if( i >= (singleSize - shouldExistSignalSize)){
                //定义每个信号所在的圆的半径
                float radius = signalRadius * i;
                @SuppressLint("DrawAllocation") RectF rectf = new RectF(radius, radius, wifiLength - radius, wifiLength - radius);
                if (i < singleSize - 1) {
                    mPaint.setStyle(Paint.Style.STROKE);
                    canvas.drawArc(rectf, startAngle, sweepAngle, false, mPaint);
                } else {
                    mPaint.setStyle(Paint.Style.FILL);
                    canvas.drawArc(rectf, startAngle, sweepAngle, true, mPaint);
                }
            }
        }
        canvas.restore();
    }
}

