package com.example.qyz6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author xilinhan
 * @description:
 * @date :2022/3/30 22:57
 */
public class FackMask extends View {

    private static final String TAG = "FackMask";

    private Paint paint;
    private Rect rect;

    private int left;
    private int top;
    private int right;
    private int bottom;

    private Canvas canvas;


    public FackMask(Context context){
        super(context);
    }

    public FackMask(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FackMask(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    /**
     * 外部调用的接口
     * */
    public void setRect(int left,int top,int right,int bottom){
        this.left=left;
        this.top=top;
        this.right=right;
        this.bottom=bottom;
        Log.d(TAG, "setRect: 控件");

        invalidate();//更新调用onDraw重新绘制
    }



    private void initPaint(){
        paint=new Paint();
        left=100;
        top=100;
        right=300;
        bottom=300;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);//设置空心
        rect=new Rect(left,top,right,bottom);
        canvas.drawRect(rect,paint);
    }

}
