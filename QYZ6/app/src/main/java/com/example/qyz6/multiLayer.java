package com.example.qyz6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author xilinhan
 * @description:
 * @date :2022/4/22 22:01
 */
public class multiLayer extends View {

    Paint mPaint;

    public multiLayer(Context context) {
        super(context);
    }

    public multiLayer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(150, 150, 100, mPaint);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.saveLayerAlpha(0, 0, 400, 400, 127);
        }
        mPaint.setColor(Color.RED);
        canvas.drawCircle(200, 200, 100, mPaint);
        canvas.restore();
    }
}
