package com.example.coolweather.draw;

import android.animation.ValueAnimator;

/**
 * @author xilinhan
 * @description:
 * @date :2022/3/17 21:43
 */
public class MyStartTranslate {
    public void MyStartTranslate(){
        MyStartTranslate(0, 0, 200, 200, 1000);
    }

    private void MyStartTranslate(int startLeft, int startTop, int toLeft, int toTop, int duration) {
        //使用ValueAnimator创建一个过程
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = (Float) animation.getAnimatedValue();


            }
        });
        valueAnimator.start();

    }
}
