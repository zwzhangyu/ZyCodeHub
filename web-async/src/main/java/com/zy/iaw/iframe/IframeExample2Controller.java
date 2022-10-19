package com.zy.iaw.iframe;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@Controller
@Slf4j
@RequestMapping("/iframe/example2")
public class IframeExample2Controller    {

    //私有成员变量countsArrayList数组是可变数组，数组存储的类型是HttpServletResponse类型
    //其允许保存所有元素，包括null，并可以根据索引位置对集合进行快速的随机访问
    private ArrayList<HttpServletResponse> connections = null; //声明一个数组类型的引用变量
    private MessageSendServlet messageSend = null; //声明一个类的对象

    //当访问这个servlet时进行初始化
    public void init() {
        connections = new ArrayList<>();
        //为这个引用变量分配内存地址
        messageSend = new MessageSendServlet(connections);
        //为这个对象分配内存地址，同时传入这个对象初始化所需要的参数
        Thread messageThread = new Thread(messageSend);
        messageThread.start(); //这两句代码为对象启动一个线程
    }


    @GetMapping("/index")
    public String index() {

        return "iframe/example2/index";
    }


    @PostMapping("/sendMessage")
    public void showStr(String username, String message, HttpServletResponse response, HttpServletRequest request) throws Exception {
        //设置请求的编码格式为UTF-8
        request.setCharacterEncoding("UTF-8");
        try {
            MessageSendServlet.messages.put("[" + request.
                    getParameter("username") + "]: " + request.getParameter("message"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/comet")
    public void comet(HttpServletResponse response) {
        connections.add(response);
    }


}
