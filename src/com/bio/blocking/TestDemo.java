package com.bio.blocking;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TestDemo {


    /**
     * accept()
     * 此方法会产生阻塞，直到有一个客户端接入，阻塞放开
     */
    @Test
    public  void testAccept() throws Exception{
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        System.out.println("hello");
    }

    /**
     *
     * The connection
     * will then block until established or an error occurs.
     * connect()方法也会产生阻塞，阻塞放开的条件是真正建立连接或者有异常抛出
     */
    @Test
    public void testConnect() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1",8888));

        while(true);//保证客户端一直开启
    }


    /**
     * This method
     * blocks until input data is available, the end of the stream is detected,
     * or an exception is thrown.
     */
    @Test
    public  void testAccept_Read() throws Exception{
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();

        InputStream in = socket.getInputStream();
        //服务端和客户端连接之后，读数据，但是客户端并不发数据
        in.read();

        System.out.println("hello");
    }
}
