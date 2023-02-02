package com.zy.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 构建HTTP代理服务器
 *
 * @author zhangyu
 */
public class Proxy {
    public static void main(String[] args) throws IOException {
        // 被代理的远程服务器IP
        String proxyHost = "127.0.0.1";
        // 被代理的远程服务器端口
        int remotePort = 8888;
        // 当前代理服务器程序监听的端口
        int localPort = 7777;
        runProxyServer(proxyHost, remotePort, localPort);
    }


    private static void runProxyServer(String proxyHost, int remotePort, int localPort) throws IOException {
        // 代理服务器启动监听
        ServerSocket serverSocket = new ServerSocket(localPort);
        final byte[] requestByte = new byte[1024];
        byte[] reply = new byte[4096];
        while (true) {
            Socket acceptClient = serverSocket.accept();
            // 代理服务器监听数据
            InputStream inputStreamClient = acceptClient.getInputStream();
            OutputStream outputStreamClient = acceptClient.getOutputStream();
            Socket socketServer;
            // 连接远程服务器
            socketServer = getSocketServer(proxyHost, remotePort, acceptClient, outputStreamClient);
            if (socketServer == null) continue;
            InputStream inputStreamServer = socketServer.getInputStream();
            OutputStream outputStreamServer = socketServer.getOutputStream();
            // 创建一个线程读取客户端请求，并代理转发
            startClientSThread(requestByte, inputStreamClient, outputStreamServer);
            // 创建一个线程处理服务器端响应数据
            startServerThread(reply, outputStreamClient, inputStreamServer);
        }
    }

    private static Socket getSocketServer(String proxyHost, int remotePort, Socket acceptClient, OutputStream outputStreamClient) throws IOException {
        Socket socketServer;
        try {
            // 链接远程服务器
            socketServer = new Socket(proxyHost, remotePort);
        } catch (IOException e) {
            PrintStream printStream = new PrintStream(outputStreamClient);
            printStream.print("代理服务器连接远程服务器异常： " + proxyHost + ":" + remotePort + ":\n" + e + "\n");
            printStream.flush();
            acceptClient.close();
            return null;
        }
        return socketServer;
    }

    private static void startClientSThread(byte[] requestByte, InputStream inputStreamClient, OutputStream outputStreamServer) {
        Thread clentThread = new Thread(() -> {
            int readByte;
            try {
                while ((readByte = inputStreamClient.read(requestByte)) != -1) {
                    System.out.println("代理服务器读取的客户端数据：" + new String(requestByte, 0, readByte));
                    outputStreamServer.write(requestByte, 0, readByte);
                    outputStreamServer.flush();
                }
            } catch (IOException e) {
                // 断开连接
            } finally {
                // 关闭流
                try {
                    outputStreamServer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        clentThread.start();
    }

    private static void startServerThread(byte[] reply, OutputStream outputStreamClient, InputStream inputStreamServer) {
        Thread serverThread = new Thread(() -> {
            int readByte;
            try {
                while ((readByte = inputStreamServer.read(reply)) != -1) {
                    System.out.println("代理服务器读取的服务器响应数据：" + new String(reply, 0, readByte));
                    outputStreamClient.write(reply, 0, readByte);
                    outputStreamClient.flush();
                }
            } catch (IOException e) {
                // 断开连接
            } finally {
                try {
                    outputStreamClient.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        serverThread.start();
    }
}
