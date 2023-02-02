package com.example.demo.thread.interrupt.volatile_demo;

import java.util.concurrent.BlockingQueue;

public class Consumer       {

    private final BlockingQueue blockingQueue;

    public Consumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }


    public boolean needMoreNums() {
        return   Math.random() <= 0.97;
    }

}
