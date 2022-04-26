package com.example.qyz6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowMetrics;

import androidx.annotation.Nullable;

/**
 * @author xilinhan
 * @description:
 * @date :2022/4/22 10:55
 */

public class MyClock extends View {

    private int mWidth;
    private int mHeight;
    Context mContext;
    int mRadius;

    //在java代码中声明一个View的时候，即new的时候会调用
    public MyClock(Context context) {
        super(context);
    }

    //在xml布局文件中使用时会自动调用，因此，如果是在布局文件中引用的，要在这里进行初始化操作
    public MyClock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    //不会自动调用，如果有默认的style的时候，在第二个构造方法中进行调用
    public MyClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawDegree(canvas);
        drawPointer(canvas);
    }

    private void drawPointer(Canvas canvas) {
        Paint paintHour = new Paint();
        paintHour.setStrokeWidth(20);
        Paint paintMinute = new Paint();
        paintMinute.setStrokeWidth(10);
        canvas.save();
    }

    private void drawDegree(Canvas canvas) {
        Paint paintDegree = new Paint();
        paintDegree.setStrokeWidth(3);
        for (int i = 0; i < 24; i++) {
            //区分整点与非整点
            if(i==0 || i==6 || i==12 || i == 18){
                //表示为整点
                paintDegree.setStrokeWidth(5);
                paintDegree.setTextSize(30);
                canvas.drawLine(mWidth >> 1, mHeight >> 1 - mRadius, mWidth >> 1, mHeight >> 1 - mRadius + 60, paintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree, (mWidth >> 1) - (paintDegree.measureText(degree)/2), mHeight >> 1 - mWidth >> 1 + 90, paintDegree);
            }else {
                paintDegree.setStrokeWidth(3);
                paintDegree.setTextSize(15);
                canvas.drawLine(mWidth >> 1, mHeight >> 1 - mRadius, mWidth >> 1, mHeight >> 1 - mRadius + 30, paintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree, (mWidth >> 1) - (paintDegree.measureText(degree)/2), mHeight >> 1 - mWidth >> 1 + 60, paintDegree);
            }
            canvas.rotate(15, mWidth >> 1 , mHeight >> 1);
        }
    }

    private void drawCircle(Canvas canvas) {
        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(5);
        canvas.drawCircle(mWidth >> 1,
                mHeight >> 1,
                mRadius,
                paintCircle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadius = mWidth / 2;
    }

    public void getSize(){
        WindowManager windowManager = (WindowManager) mContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            WindowMetrics metrics = windowManager.getCurrentWindowMetrics();
            int width = metrics.getBounds().width();
            int height = metrics.getBounds().height();
        }else{
            DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
            int widthPixels = displayMetrics.widthPixels;
            int heightPixels = displayMetrics.heightPixels;
        }
    }
}