package com.example.bitmapdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author xilinhan
 * @description:
 * @date :2022/3/24 19:59
 */
public class BitmapFunction extends AppCompatActivity {

    private static final String TAG = "BitmapFunction";
    final String pathImg = "D:\\andoridProject\\AndroidProject\\BitMapDemo\\app\\src\\main\\res\\drawable-v24\\ic_launcher_foreground.xml";

    public void getInf(){
        //获取图片的原始大小
        BitmapFactory.Options mOptions = new BitmapFactory.Options();
        //如果设置为true，则不会获取图片，不会分配内存，但是会返回图片的高宽信息
        mOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathImg, mOptions);
        float srcWidth = mOptions.outWidth;
        float srcHeight = mOptions.outHeight;
        Log.d(TAG, "getInf: srcWidth is: " + srcWidth + ", " + "srcHeight is: " + srcHeight);
    }

    private void makeSample(){
        //1 获取图片的原始大小
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//如果设置为true，不获取图片，不分配内存，但会返回图片的高度宽度信息。
        BitmapFactory.decodeFile(pathImg, options);

        float srcWidth = options.outWidth;//获取图片的宽度值
        float srcHeight = options.outHeight;//获取图片的高度值

        //2 得到目标显示的大小
        int width = 300;
        int height = 300;

        //3 得到缩放比例
        int inSampleSize = 1;
        if(srcHeight > height || srcWidth > width){
            if(srcWidth > srcHeight){
                inSampleSize = Math.round(srcWidth / width);
            }else {
                inSampleSize = Math.round(srcHeight / height);
            }
        }

        //4 进行缩放
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;

        Bitmap bitmap = BitmapFactory.decodeFile(pathImg, options);

    }

    private void matrixImg(){
        try {
            InputStream inputStream = new FileInputStream(pathImg);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            //matrix是一个三×三的矩阵
            Matrix matrix = new Matrix();
            matrix.postScale(4.0f, 4.0f);
            matrix.postRotate(45f);
            Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getBitmapFromFile(){
        //使用Bitmap decodeFile的方式进行获取Bitmap
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(pathImg, options);

        //推荐方法，使用Bitmap decodeFileDescriptor的方式进行获取Bitmap，这种方式直接调用JNI函数进行读取，效率比较高
        try {
            FileInputStream inputStream = new FileInputStream(pathImg);
            BitmapFactory.Options options1 = new BitmapFactory.Options();
            Bitmap bitmap1 = BitmapFactory.decodeFileDescriptor(inputStream.getFD(), null, options1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getBitmapFromStream(){
        try {
            FileInputStream fileInputStream = new FileInputStream(pathImg);
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream, null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getBitmapFromRes(){
        //此方法相当耗费内存，一般可以采用decodeStream进行代替，原因是decodeResource加载的图片可能会经过缩放，该缩放是放在java层进行的
        //效率比较低，并且需要消耗java层的内存，因此，如果大量使用该接口进行加载图片，同意导致OOM错误
        //这两个接口各有用处，如果对性能要求较高，则应该使用 decodeStream；如果对性能要求不高，且需要 Android 自带的图片自适应缩放功能，则可以使用 decodeResource。
        BitmapFactory.Options options = new BitmapFactory.Options();
        Resources resources = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background, options);
    }

    private Bitmap drawableToBitmap(Drawable drawable){
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private void getDrawable(){
        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher_background);

    }

    private void bitmapToDrawable(){
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(pathImg);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }





}
