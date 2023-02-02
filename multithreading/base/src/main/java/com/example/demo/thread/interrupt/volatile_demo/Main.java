package com.example.demo.thread.interrupt.volatile_demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(8);
        Producer producer = new Producer(blockingQueue);
        Thread thread = new Thread(producer);
        thread.start();
        //主线程休眠，保证生产者先生产足够的数据
        Thread.sleep(500);
        Consumer consumer = new Consumer(blockingQueue);
        if (consumer.needMoreNums()){
            System.out.println("消费数据："+blockingQueue.take());
            Thread.sleep(100);
        }
        producer.flag=true;
        System.out.println(producer.flag);
    }
}
