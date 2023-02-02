package com.example.demo.thread.interrupt;

public class MyThread1 implements Runnable {

    @Override
    public void run() {
        int count = 0;
        while(!Thread.currentThread().isInterrupted()&& count < 10000) {
            System.out.println("当前计数："+count);
            count++;
            try {
                //设置线程休眠
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                //当前再次中断，本次中断线程会停止
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyThread1());
        thread.start();
        Thread.sleep(5);
        //中断当前线程
        thread.interrupt();
    }
}