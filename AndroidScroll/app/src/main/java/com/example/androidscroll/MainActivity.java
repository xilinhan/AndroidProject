package com.example.androidscroll;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int[] screenLocation = new int[2];
    int[] windowLocation = new int[2];
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.button);
        final TextView textView = (TextView) findViewById(R.id.textView);
        mButton.post(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                //以屏幕的左上角为原点
                mButton.getLocationOnScreen(screenLocation);
                //以父窗口（非父布局）的左上角为原点
                mButton.getLocationInWindow(windowLocation);
                textView.setText("dialog_screenX::" + screenLocation[0] + "   dialog_screenY::" + screenLocation[1] + "\n" +
                        "dialog_windowX::" + windowLocation[0] + "   dialog_windowY::" + windowLocation[1]);
                Log.e("TAG", "screenX::" + screenLocation[0] + "   screenY::" + screenLocation[1]);
                Log.e("TAG", "windowX::" + windowLocation[0] + "   windowY::" + windowLocation[1]);

                /*
                * getLocationInWindow()是相对于父窗口而非父布局的；
                  getLocationOnScreen()是相对于手机屏幕左上角；
                  getLocationInWindow()和getLocationOnScreen()在非弹窗中通常情况下是一致的
                * */
            }
        });

        //模拟弹窗，在AlertDialog中进行展示
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
        mButton.post(new Runnable() {
            @Override
            public void run() {
                //以屏幕的左上角为原点
                mButton.getLocationOnScreen(screenLocation);

                //以父窗口（非父布局）的左上角为原点
                mButton.getLocationInWindow(windowLocation);
                textView.setText("dialog_screenX::" + screenLocation[0] + "   dialog_screenY::" + screenLocation[1] + "\n" +
                        "dialog_windowX::" + windowLocation[0] + "   dialog_windowY::" + windowLocation[1]);
                Log.e("TAG", "dialog_screenX::" + screenLocation[0] + "   dialog_screenY::" + screenLocation[1]);
                Log.e("TAG", "dialog_windowX::" + windowLocation[0] + "   dialog_windowY::" + windowLocation[1]);
            }
        });
        builder.setView(contentView);
        builder.show();


        /*
            getLocationOnScreen()依然是相对于手机屏幕左上角；
            而getLocationInWindow()是相对于父窗口的左上角，此时父窗口不再是页面的窗口了，
            而是Dialog的窗口，故和getLocationOnScreen()不再一致；
         */

    }
}