package com.example.demo.thread.producer_consumer_4;


import java.util.LinkedList;

/***
 *
 *  自定义阻塞队列
 * @author ZhangYu
 * @date 2021/10/3
 */
public class MyBlockingQueue {
    /**  容器允许存放的最大数量  **/
    private final int maxSize;
    /**  容器  **/
    private final  LinkedList<Double> container;

    public MyBlockingQueue(int maxSize ) {
        this.maxSize = maxSize;
        this.container = new LinkedList<>();
    }

    /**
     *  往队列添加元素，如果队列已满则阻塞线程
     */
    public  synchronized  void put(Double data){
        //如果队列已满，则阻塞生产者线程
        while (container.size()==maxSize){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //队列未满则添加元素，并通知消费者消费数据
        container.add(data);
        notifyAll();
    }

    /**
     *  从队列取出数据，如果队列为空则阻塞
     * @return  队列元素
     */
    public synchronized  Double take(){
        //如果队列为空，则消费者停止消费
        while (container.size()==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //队列不为空则消费数据，并通知生产者继续生产数据
        Double data = container.poll();
        notifyAll();
        return data;
    }

}
