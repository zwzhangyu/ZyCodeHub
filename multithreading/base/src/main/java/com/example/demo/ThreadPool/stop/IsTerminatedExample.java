package com.example.demo.ThreadPool.stop;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class IsTerminatedExample {



    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor service = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(20));
        for (int i = 0; i < 5; i++) {
            service.execute(new MyTask("编号"+i));
        }
        service.shutdown();
        System.out.println(service.isTerminated());
        Thread.sleep(5000);
        System.out.println(service.isTerminated());
    }
}
