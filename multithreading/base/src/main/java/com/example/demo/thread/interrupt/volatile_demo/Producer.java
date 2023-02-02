package com.example.demo.thread.interrupt.volatile_demo;

import java.util.concurrent.BlockingQueue;

public class Producer implements  Runnable{
    /**  标识位，默认为false **/
    public  volatile boolean flag=false;
    /**  阻塞队列 **/
    private final BlockingQueue blockingQueue;

    public Producer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
                while ( !flag ){
                    double random = Math.random();
                    blockingQueue.put(random);
                    System.out.println("生产数据"+random);
                }
        } catch (InterruptedException e) {
                e.printStackTrace();
        }finally {
                System.out.println("生产者线程结束");
        }
    }
}
