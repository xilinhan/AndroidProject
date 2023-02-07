package com.example.cede131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;

/**
 * @author xilinhan
 * @description: 负责处理每条线程通信的线程类
 * @date :2022/6/20 18:24
 */
public class ServiceThread implements Runnable {

    Socket mSocket = null;
    BufferedReader mBufferedReader = null;

    public ServiceThread(Socket socket) {
        this.mSocket = socket;
        try {
            mBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String content = null;
        //采用循环不断从Socket中读取客户端发来的数据
        while (true) {
            if ((content = readFromClient()) != null){
                for (Iterator<Socket> it = MyServer.sSocketArrayList.iterator(); it.hasNext();
                     ) {
                    Socket socket = it.next();
                    try {
                        OutputStream stream = socket.getOutputStream();
                        stream.write(content.getBytes("utf-8"));
                    } catch (IOException e) {
                        e.printStackTrace();
                        //删除该Socket
                        it.remove();
                    }
                }
            }
        }
    }

    private String readFromClient(){
        try {
            return mBufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            MyServer.sSocketArrayList.remove(mSocket);
        }
        return null;
    }
}
