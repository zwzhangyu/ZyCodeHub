package com.example.demo.thread.producer_consumer_4;


public class Main {
    public static void main(String[] args)   {
        //创建一个阻塞队列，容量为8
        MyBlockingQueue queue= new MyBlockingQueue(8);
        //创建生产者线程
        Runnable producer=()->{
            while (true){
                    double random = Math.random();
                    queue.put(random);
                    System.out.println("生产数据==>"+random);
            }};
        new Thread(producer).start();
        //创建消费者线程
        Runnable consumer=()->{
            while (true){
                    System.out.println("消费数据==>"+queue.take());
            }};
        new Thread(consumer).start();
    }

}
