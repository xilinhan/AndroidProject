package com.example.cede131;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xilinhan
 * @description: 模拟服务端，时刻监听，然后传入一段编码为utf-8的流
 * @date :2022/6/20 16:17
 */
public class SimpleServer {
    public static void main(String[] args) throws IOException {
        //创建一个ServerSocket，用来监听客户端的Socket请求
        ServerSocket serverSocket = new ServerSocket();
        //采用循环，不断的接受来自客户端的请求
        while(true){
            //每当接收到客户端的Socket请求后，服务端也会产生一个Socket
            Socket socket = serverSocket.accept();
            OutputStream stream = socket.getOutputStream();
            stream.write("您好".getBytes("utf-8"));
            stream.close();
            socket.close();
        }
    }
}
