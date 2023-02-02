package com.example.demo.lock.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    private static  final  ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    private static  final   ReentrantReadWriteLock.ReadLock readLock= readWriteLock.readLock();
    private static  final   ReentrantReadWriteLock.WriteLock writerLock= readWriteLock.writeLock();

    public  void  read(){
        readLock.lock();
        try {
            System.out.println("执行读操作");
        }finally {
            readLock.unlock();
        }

    }
    public  void  write(Object input){
        writerLock.lock();
        try {
            System.out.println("执行写操作");
        }finally {
            writerLock.unlock();
        }

    }
}
