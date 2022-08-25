package com.example.springboot.aop.example1;

public class Main {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloService proxyBean = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
        proxyBean.sayHello("hello");
    }

}
