package com.example.demo.threadlocal;

public class Main {

    private  static ThreadLocal<Object> threadLocal=new ThreadLocal<>();
    public static void main(String[] args) {
        threadLocal.get();
    }
}
