package com.zy.proxy;

import java.io.*;
import java.net.Socket;

/**
 * 构建客户端
 *
 * @author zhangyu
 */
public class Client {

    public static void main(String[] args) {
        // 持续获取控制台输入
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = "";
        do {
            try {
                inputStr = bufferedReader.readLine();
                System.out.println("控制台读取输入：" + inputStr);
                Socket socket = new Socket("127.0.0.1", 7777);
                OutputStream outputStream = socket.getOutputStream();
                // 客户端发送数据
                InputStream inputStream = socket.getInputStream();
                outputStream.write(inputStr.getBytes());
                outputStream.flush();
                outputStream.close();
                startListenServerDataThread(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!"exit".equals(inputStr));
    }

    /**
     * 客户端发送消息后开启一个线程监听处理服务器响应信息
     */
    private static void startListenServerDataThread(InputStream inputStreamServer) {
        byte[] reply = new byte[1024];
        Thread serverThread = new Thread(() -> {
            int readByte;
            try {
                while ((readByte = inputStreamServer.read(reply)) != -1) {
                    System.out.println("客户端读取的服务器响应数据：" + new String(reply, 0, readByte));
                }
            } catch (IOException e) {
                // 断开连接
            } finally {
                try {
                    inputStreamServer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        serverThread.start();
    }
}
