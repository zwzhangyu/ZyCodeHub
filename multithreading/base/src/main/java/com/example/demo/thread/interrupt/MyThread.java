package com.example.demo.thread.interrupt;

public class MyThread implements Runnable {

    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted() &&   count < 10000) {
            System.out.println("当前计数："+count);
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyThread());
        thread.start();
        Thread.sleep(5);
        //中断当前线程
        thread.interrupt();
    }
}