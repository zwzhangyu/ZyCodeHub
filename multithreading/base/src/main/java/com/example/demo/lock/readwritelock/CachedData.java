package com.example.demo.lock.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedData {
    static Object data;
    static volatile boolean cacheValid;
    static  final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

  static   void processCachedData() {
        rwl.readLock().lock();
        if (!cacheValid) {
            //在获取写锁之前，必须首先释放读锁。
            rwl.readLock().unlock();
            //获取写锁
            rwl.writeLock().lock();
            try{
                //这里需要再次判断数据的有效性,因为在我们释放读锁和获取写锁的空隙之内，可能有其他线程修改了数据。
                if (!cacheValid) {
                    data = new Object();
                    cacheValid = true;
                }
                //在不释放写锁的情况下，直接获取读锁，这就是读写锁的降级。
                rwl.readLock().lock();
            }finally {
                //释放了写锁，但是依然持有读锁
                rwl.writeLock().unlock();
            }
        }
        try {
            System.out.println(data);
        } finally {
            //释放读锁
            rwl.readLock().unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> processCachedData(),"Thread-1").start();
        new Thread(() -> processCachedData(),"Thread-2").start();
        new Thread(() -> processCachedData(),"Thread-3").start();

    }
}
