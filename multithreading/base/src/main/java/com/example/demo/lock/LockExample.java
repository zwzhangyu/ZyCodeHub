package com.example.demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    Lock lock =new ReentrantLock();


    public void test1() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            //.....
        }
        finally {
            lock.unlock();
        }
    }
}
