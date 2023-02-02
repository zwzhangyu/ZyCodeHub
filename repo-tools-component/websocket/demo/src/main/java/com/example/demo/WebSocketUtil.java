package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

/**
 * 双工通信websocket工具类
 * @author wwl
 *
 */
@ServerEndpoint(value="/webSocket")
@Component
public class WebSocketUtil{

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketUtil> webSocketSet = new CopyOnWriteArraySet<WebSocketUtil>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private Process process;
    private InputStream inputStream;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) throws IOException {
        // 执行tail -f命令
        process = Runtime.getRuntime().exec("tail -f D:\\zhangyu\\CodeHub\\repo\\websocket\\demo\\logs\\applog\\2021-09-06\\info\\info.0.log");
        inputStream = process.getInputStream();
        // 一定要启动新的线程，防止InputStream阻塞处理WebSocket的线程
        TailLogThread thread = new TailLogThread(inputStream, session);
        thread.start();
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //发送消息
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 发送消息
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) throws IOException {
        for (WebSocketUtil item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketUtil.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketUtil.onlineCount--;
    }
}