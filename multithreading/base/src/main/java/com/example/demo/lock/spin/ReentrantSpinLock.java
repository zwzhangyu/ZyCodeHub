package com.example.demo.lock.spin;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现一个可重入的自旋锁
 */
public class ReentrantSpinLock {
    private  AtomicReference<Thread> owner=new AtomicReference<>();
    //重入次数
    private int count=0;

    public void lock(){
        Thread thread = Thread.currentThread();
        //如果同一个线程，允许重入
        if (thread==owner.get()){
            ++count;
           return;
        }
        //当没有线程使用锁时，owner保存的就是Null
        while (!owner.compareAndSet(null, thread)){
            System.out.println("自旋了...");
        }
    }


    public void unlock(){
        Thread thread = Thread.currentThread();
        if (thread==owner.get()){
            if (count>0){
                --count;
            }else {
                owner.set(null);
            }
        }
    }

    public static void main(String[] args) {
        ReentrantSpinLock spinLock = new ReentrantSpinLock();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName()+"开始尝试获取锁");
            spinLock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"已获取到锁");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
