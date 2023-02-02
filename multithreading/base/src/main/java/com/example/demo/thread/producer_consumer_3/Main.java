package com.example.demo.thread.producer_consumer_3;

/***
 *
 * @author ZhangYu
 * @date 2021/10/3
 */
public class Main {
    public static void main(String[] args)   {
        //创建一个阻塞队列，容量为8
        MyBlockingQueue<Double> queue= new MyBlockingQueue<>(8,false);
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
