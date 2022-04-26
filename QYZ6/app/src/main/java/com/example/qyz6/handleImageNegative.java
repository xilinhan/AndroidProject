package com.example.qyz6;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * @author xilinhan
 * @description:
 * @date :2022/4/25 21:38
 */
public class handleImageNegative {

    public Bitmap handleImage(Bitmap bitmap){

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int color;
        int r,g,b,a;
        int picSize = width * height;

        Bitmap bitmap1 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] oldPx = new int[picSize];
        int[] newPx = new int[picSize];
        bitmap.getPixels(oldPx, 0 ,width, 0, 0, width, height);
        for (int i = 0; i < picSize; i++) {
            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;
            if(r > 255){
                r = 255;
            }else {
                r = 0;
            }

            if(g > 255){
                g = 255;
            }else {
                g = 0;
            }

            if(b > 255){
                b = 255;
            }else {
                b = 0;
            }

            newPx[i] = Color.argb(r, g, b, a);
        }
        bitmap1.setPixels(newPx, 0, width, 0, 0, width, height);
        return bitmap1;
    }
}
