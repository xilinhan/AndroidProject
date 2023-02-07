package com.example.code92;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * @author xilinhan
 * @description:
 * @date :2022/6/18 22:19
 */
public class BindService extends Service {
    private int count;
    private boolean quit;
    private MyBinder mMyBinder = new MyBinder();
    class MyBinder extends Binder{
        public int getCount(){
            return BindService.this.count;
        }
    }

    //Service被绑定的时候回调该方法
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMyBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(() -> {
            while(!quit){
                try {
                    Thread.sleep(1000);
                    BindService.this.count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit = true;
    }
}
