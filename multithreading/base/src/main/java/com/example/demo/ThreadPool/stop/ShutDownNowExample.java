package com.example.demo.ThreadPool.stop;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ShutDownNowExample {



    public static void main(String[] args) {
        ThreadPoolExecutor service = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(20));
        for (int i = 0; i < 5; i++) {
            service.execute(new MyTask("编号"+i));
        }
        List<Runnable> runnables = service.shutdownNow();
        System.out.println("正在等待的任务数量=="+runnables.size());
        System.out.println("手动执行返回的未完成任务");
        for (Runnable runnable : runnables) {
            new Thread(runnable).start();
        }
    }
}
