package com.example.demo.thread.producer_consumer_3;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/***
 *
 *  自定义阻塞队列
 * @author ZhangYu
 * @date 2021/10/3
 */
public class MyBlockingQueue<E> {

    /** 队列容器 */
    private final Queue queue;

    /** 队列容量 */
    private final int capacity;

    /** 对象锁 */
    final ReentrantLock lock;

    /** 等待取出数据条件 */
    private final Condition notEmpty;

    /** 等待添加数据条件 */
    private final Condition notFull;

    /**
     * 初始化阻塞队列
     * @param capacity  队列容量
     * @param fair  是否公平锁
     */
    public MyBlockingQueue(int capacity, boolean fair) {
        this.queue = new LinkedList();
        this.capacity=capacity;
        this.lock = new ReentrantLock(fair);
        this.notEmpty = lock.newCondition();
        this.notFull =  lock.newCondition();
    }


    /**
     *   往队列插入元素，如果队列大小到达容量限制则阻塞
     * @param e 插入元素
     * @throws InterruptedException 中断异常
     */
    public  void put(E e) throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lock();
      try{
          while (queue.size()==capacity){
              notFull.await();
          }
          queue.add(e);
          notEmpty.signalAll();
      } finally {
          lock.unlock();
      }

    }

    /**
     *   从队列取出一个元素，如果队列为空则阻塞
     * @return 队列元素
     * @throws InterruptedException 中断异常
     */
    public  E take()throws  InterruptedException{
        final ReentrantLock lock = this.lock;
        lock.lock();
        try{
            while (queue.size()==0){
                notEmpty.await();
            }
            E element = (E) queue.remove();
            notFull.signalAll();
            return   element;
        } finally {
            lock.unlock();
        }
    }
}
