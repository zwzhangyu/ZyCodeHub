package com.example.demo.ThreadPool.threadfactory;


import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor {


    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1, 4, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new SimpleThreadFactory("自定义线程池")
        );
        for (int i = 0; i < 5; i++) {
            threadPool.submit(() -> System.out.println("测试线程池"));
        }
        threadPool.shutdown();


    }

}
