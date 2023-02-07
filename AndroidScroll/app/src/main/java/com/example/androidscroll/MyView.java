package com.example.androidscroll;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.ViewDragHelper;

/**
 * @author xilinhan
 * @description:
 * @date :2022/3/27 22:19
 */
public class MyView extends View {

    private int defaultSize;
    private int mLastX;
    private int mLastY;
    private Scroller mScroller;

    public void setOnCircleAnimationStartListener(OnCircleAnimationStartListener onCircleAnimationStartListener) {
        mOnCircleAnimationStartListener = onCircleAnimationStartListener;
    }

    private OnCircleAnimationStartListener mOnCircleAnimationStartListener;


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View inflate = inflate(getContext(), R.layout.my_view, null);
        //第二个参数就是在我们style.xml文件中的<declare-styleable.MyView>标签
        //即属性集合的标签
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        //第一个参数为属性集合里面的属性，R文件名称：R.styleable+属性集合名称+下划线+属性名称
        //第二个参数为，如果没有设置这个属性，则设置的默认的值
        defaultSize = array.getDimensionPixelSize(R.styleable.MyView_default_size, 100);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(defaultSize, widthMeasureSpec);
        int height = getMySize(defaultSize, heightMeasureSpec);
        if(width < height){
            height = width;
        }else {
            width = height;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //这里需要调用父类View的onDraw函数，因为View这个类帮我们实现了一些基本的绘制功能，比如绘制背景颜色或者背景图片等
        super.onDraw(canvas);
        int r = getMeasuredWidth() / 2;
        //圆心的横坐标为当前的View的左边起始位置+半径
        int centerX = getLeft() + r;
        int centerY = getTop() + r;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        //开始绘制
        canvas.drawCircle(centerX, centerY, r, paint);

    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode){
            case MeasureSpec.UNSPECIFIED:
                mySize = defaultSize;
                break;
            case MeasureSpec.AT_MOST:
                mySize = Math.min(size, defaultSize);
                break;
            case MeasureSpec.EXACTLY:
                mySize = size;
                break;
        }
        return mySize;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取坐标
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        //判断点击事件
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //记录触摸点的坐标
                mLastX = rawX;
                mLastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算偏移量
                int offsetX = rawX - mLastX;
                int offsetY = rawY - mLastY;
                //使用layout进行绘制，此为方法一
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + mLastX,
                        getBottom() + offsetY);
                //使用API封装的方法进行绘制，此为方法二
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                //使用LayoutParams参数进行设置
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
                layoutParams.leftMargin = getLeft() + offsetX;
                layoutParams.topMargin = getTop() + offsetY;
                //更新初始坐标
                mLastX = rawX;
                mLastY = rawY;
                break;
        }
        return true;
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        mScroller = new Scroller(getContext());
        //判断Scroller是否执行完毕
        if(mScroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(
                    mScroller.getCurrX(),mScroller.getCurrY()
            );
            //通过重绘来不断调用computeScroll
            invalidate();
        }
    }

}
