package com.zy.iaw.iframe;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class MessageSendServlet implements Runnable {
    private ArrayList<HttpServletResponse> connections;
    int i;
    public static ArrayBlockingQueue<String> messages = new ArrayBlockingQueue<String>(10);

    // FIFO模式，先进先出，当队列为空时线程阻塞，当队列满时阻塞
    public MessageSendServlet(ArrayList<HttpServletResponse>
                                      connections) {
        this.connections = connections;
    }//初始化将用comet servlet传过来的参数初始化

    //继承runnable必须实现类，线程运行中所要做的事情都在这里面实现
    public void run() { //一个死循环
        while (true) {
            String message = null;
            try {
                message = messages.take(); //获取用户发送的信息
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (connections) {
                for (HttpServletResponse response : connections) {
                    //遍历所有的用户，将某个用户发送的信息传送到每个建立连接的客户端
                    try {
                        PrintWriter out = response.getWriter();
                        out.println("<script>parent.addMessage('" + message + "<br>')</script>");
                        System.out.println(message);
                        System.out.println(i++);
                        out.flush();
                    } catch (Exception e) {
                        System.out.println("dd");
                    }
                }
            }
        }
    }
}