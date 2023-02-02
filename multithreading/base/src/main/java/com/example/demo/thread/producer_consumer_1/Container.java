package com.example.demo.thread.producer_consumer_1;


import java.util.LinkedList;

/**
 * 自定义容器
 */
public class Container {
    /**  容器允许存放的最大数量  **/
    private final int maxSize;
    /**  容器  **/
    private final  LinkedList<Integer> container;

    public Container(int maxSize, LinkedList<Integer> container) {
        this.maxSize = maxSize;
        this.container = container;
    }

    /**
     *  往队列添加元素，如果队列已满则阻塞线程
     */
    public  synchronized  void put(Integer data){
        //如果队列已满，则阻塞生产者线程
        if (container.size()==maxSize){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //队列未满则添加元素，并通知消费者消费数据
        container.add(data);
        notify();
    }

    /**
     *  从队列取出数据，如果队列为空则阻塞
     * @return  队列元素
     */
    public synchronized  Integer take(){
        //如果队列为空，则消费者停止消费
        while (container.size()==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //队列不为空则消费数据，并通知生产者继续生产数据
        Integer remove = container.poll();
        notify();
        return remove;
    }


}
