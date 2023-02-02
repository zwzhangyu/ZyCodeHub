package com.example.demo.thread.producer_consumer_2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/***
 *
 *   使用阻塞队列BlockingQueue实现生产者消费者模式
 * @author ZhangYu
 * @date 2021/10/3
 */
public class Main {
    public static void main(String[] args)   {
         //创建一个阻塞队列，容量为8
        BlockingQueue<Double> queue= new ArrayBlockingQueue<>(8);
         //创建生产者线程
        Runnable producer=()->{
            while (true){
                try {
                    double random = Math.random();
                    queue.put(random);
                    System.out.println("生产数据==>"+random);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }};
        new Thread(producer).start();
        //创建消费者线程
        Runnable consumer=()->{
            while (true){
                try {
                    System.out.println("消费数据==>"+queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }};
         new Thread(consumer).start();
    }
}
