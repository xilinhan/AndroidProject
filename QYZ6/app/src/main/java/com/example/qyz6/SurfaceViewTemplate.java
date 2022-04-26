package com.example.qyz6;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

/**
 * @author xilinhan
 * @description:
 * @date :2022/4/25 22:39
 */
public class SurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback,Runnable{

    //SurfaceHolder
    private SurfaceHolder mSurfaceHolder;
    //用于绘图的Canvas
    private Canvas mCanvas;
    //子线程标志位
    private boolean mIsDrawing;

    public SurfaceViewTemplate(Context context) {
        super(context);
        initView();
    }


    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    //SurfaceHolder是一个接口，其作用就像一个关于Surface的监听器。 提供访问和控制SurfaceView背后的Surface 相关的方法 （providingaccess and control over this SurfaceView's underlying surface），它通过三个回调方法，让我们可以感知到Surface的创建、销毁或者改变。
    private void initView() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void run() {

    }
}
