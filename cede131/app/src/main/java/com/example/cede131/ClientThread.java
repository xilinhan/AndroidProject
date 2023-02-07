package com.example.cede131;

import android.os.Looper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Handler;

/**
 * @author xilinhan
 * @description:
 * @date :2022/6/20 18:58
 */
public class ClientThread implements Runnable{
    //定义向UI线程发送消息的Handler对象
    private Handler mHandler;
    private BufferedReader mBufferedReader;
    private OutputStream mOutputStream;
    //定义接收UI线程的消息的Handler对象
    Handler revHandler;
    ClientThread(Handler m){
        mHandler = m;
    }



    @Override
    public void run() {
        try {
            Socket socket = new Socket("192.168.1.88",3000);
            mBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            mOutputStream = socket.getOutputStream();
            //启动一条子线程来读取服务响应的数据
            new Thread(() -> {

            }).start();
            Looper.prepare();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
