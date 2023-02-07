package com.example.cede131;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author xilinhan
 * @description:
 * @date :2022/6/20 18:20
 */
public class MyServer {
    //定义保存所有Socket的ArrayList
    public static ArrayList<Socket> sSocketArrayList = new ArrayList<Socket>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(30000);
        while(true){
            //此行代码会阻塞，将一直等待别人的连接
            Socket socket = serverSocket.accept();
            sSocketArrayList.add(socket);
            //每当客户端连接过后，启动一条ServerThread线程为该客户端服务
            new Thread(new ServiceThread(socket)).start();
        }

    }
}
