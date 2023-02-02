package com.example.demo.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo1 {


    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getId()+"执行第一部分任务");
        try {
            Thread.sleep((long) (Math.random()*2000));
            latch.await();
            System.out.println(Thread.currentThread().getId()+"执行第二部分任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }).start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getId()+"执行第一部分任务");
            try {
                Thread.sleep((long) (Math.random()*2000));
                latch.await();
                System.out.println(Thread.currentThread().getId()+"执行第二部分任务");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(3000);
        //当前线程发出通知信号
        latch.countDown();
    }
}
