package com.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStart {

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8888);

        while (true){
            Socket socket = serverSocket.accept();
            new Thread(new ClientRunner(socket)).start();
        }
    }
}


class ClientRunner implements Runnable{

    private Socket socket;

    public ClientRunner(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] data = new byte[10];
            inputStream.read(data);
            System.out.println("服务端收到数据！"
                    +new String(data)
                    +"线程编号："
                    +Thread.currentThread().getId()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
