package com.example.demo.thread.interrupt.volatile_demo;

public class MyThread implements Runnable {

    private volatile boolean flag = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (!flag && num <= 1000000) {
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("线程终止");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
        Thread.sleep(3000);
        myThread.flag = true;
    }
}