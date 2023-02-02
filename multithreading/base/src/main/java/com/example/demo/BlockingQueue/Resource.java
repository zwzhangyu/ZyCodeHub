package com.example.demo.BlockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/***
 *
 *  生产者-消费者模型
 * @author ZhangYu
 * @date 2021/9/20
 */
public class Resource {
    private  String  name;
    private  int count=1;
    private boolean flag;
    private  Lock lock=new ReentrantLock();
    private  Condition productCondition=lock.newCondition();
    private  Condition consumerCondition=lock.newCondition();

    public void  product(String name){
        lock.lock();
        try {
            while (flag){
                try {
                    //把线程加入到生产者线程池
                    productCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.name=name;
            count++;
            System.out.println(Thread.currentThread().getName() + "...生产者..." + this.name);
            flag=true;
            //唤醒一个消费者线程
            consumerCondition.signal();
        }finally {
            lock.unlock();
        }

    }

    public void  consumer(String name){
        lock.lock();
        try {
            while (!flag){
                try {
                    consumerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "...消费者..." + this.name);
            flag=false;
            //唤醒一个生产者线程
            productCondition.signal();
        }finally {
            lock.unlock();
        }

    }

    static class  ProductThread extends  Thread{
      private  Resource resource;

        public ProductThread(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            while (true) {
                resource.product("生产资源");

            }
        }
    }
    static   class  ConsumerThread extends   Thread{
        private  Resource resource;

        public ConsumerThread(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
          while (true) {
              resource.consumer("消费资源");
          }
        }
    }


    public static void main(String[] args) {
        Resource resource = new Resource();
        ProductThread productThread1 = new ProductThread(resource);
        ProductThread productThread2 = new ProductThread(resource);
        productThread1.start();
        productThread2.start();
        ConsumerThread consumerThread1 = new ConsumerThread(resource);
        ConsumerThread consumerThread2 = new ConsumerThread(resource);
        consumerThread1.start();
        consumerThread2.start();

    }



}
