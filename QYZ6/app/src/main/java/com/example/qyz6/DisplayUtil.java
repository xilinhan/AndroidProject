package com.example.qyz6;

import android.content.Context;

/**
 * @author xilinhan
 * @description:
 * @date :2022/3/30 20:49
 */
public class DisplayUtil {


    private float mDensity;

    private Context mContext;

    private float mScaledDensity;

    public DisplayUtil() {
    }

    public DisplayUtil(Context context) {
        mContext = context;
        mDensity = mContext.getResources().getDisplayMetrics().density;
        mScaledDensity = mContext.getResources().getDisplayMetrics().scaledDensity;
    }

    public int px2dip(float pxValue){
        return (int)(pxValue / mDensity + 0.5f);
    }

    public int dip2px(float dipValue){
        return (int)(dipValue * mDensity + 0.5f);
    }

    public int sp2dip(float spValue){
        return (int)(spValue / mScaledDensity + 0.5f);
    }

    public int dip2sp(float dipValue){
        return (int)(dipValue * mScaledDensity + 0.5f);
    }
}
