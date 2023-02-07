package com.example.code72;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

/**
 * @author xilinhan
 * @description:
 * @date :2022/6/16 16:29
 */
public class GameView extends View {

    private Paint mPaint;
    private RadialGradient radialGradient;
    private boolean isLose;
    //屏幕高度
    private float tableHeight;
    //屏幕宽度
    private float tableWidth;

    //小球的坐标
    private Random mRandom = new Random();
    private float ballX = mRandom.nextInt(200) + 20f;
    private float ballY = mRandom.nextInt(10) + 60f;

    private void init(){
        mPaint = new Paint();
        radialGradient = new RadialGradient(0, 0, 0, 0, 0, Shader.TileMode.CLAMP);
        setFocusable(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }



    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isLose){
            mPaint.setColor(Color.RED);
            mPaint.setTextSize(40f);
            canvas.drawText("游戏已经结束",tableWidth/2-100,200f,mPaint);
        }else {
            //保存坐标系统
            canvas.save();
            //将坐标系统平移到小球圆心处 来绘制小球
            canvas.translate(ballX, ballY);
            //设置渐变，并且绘制小球
            mPaint.setShader(radialGradient);
            canvas.drawCircle(0f,0f,10,mPaint);
            //恢复原来的坐标系统
            canvas.restore();
            //绘制球拍
            //TODO
        }
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
