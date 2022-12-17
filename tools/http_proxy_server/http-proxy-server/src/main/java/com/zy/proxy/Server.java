package com.zy.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 构建服务器端
 *
 * @author zhangyu
 */
public class Server {

    public static void main(String[] args) throws IOException {
        // 启动服务器
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            OutputStream acceptOutputStream = accept.getOutputStream();
            handlerAcceptData(inputStream, acceptOutputStream);
        }
    }

    /**
     * 开启一个线程接受和响应客户端信息
     */
    private static void handlerAcceptData(InputStream inputStreamClient, OutputStream acceptOutputStream) {
        byte[] bytes = new byte[1024];
        Thread serverThread = new Thread(() -> {
            int readByte;
            try {
                while ((readByte = inputStreamClient.read(bytes)) != -1) {
                    System.out.println("服务器端接收数据：" + new String(bytes, 0, readByte));
                    // 服务器端响应客户端请求信息
                    acceptOutputStream.write("ack".getBytes());
                    acceptOutputStream.flush();
                }
            } catch (IOException e) {
                // 断开连接
            } finally {
                // 关闭流
                try {
                    inputStreamClient.close();
                    acceptOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        serverThread.start();
    }
}
