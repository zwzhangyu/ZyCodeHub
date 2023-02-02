package com.zy.design_pattern.callback.example1.demo5;


import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {
    //存放构造器送入的值
    private String msg;

    //构造器参数可以随意设置，这里为了方便调试，设置为字符串
    public MyEvent(String msg) {
        super(msg);
        this.msg = msg;
    }

    //自定义一个方法，这个方法也可以随意写，这里也是测试用
    public void myevent() {
        System.out.println("********My event**************");
        System.out.println(msg);
        System.out.println("*******************************");
    }
}