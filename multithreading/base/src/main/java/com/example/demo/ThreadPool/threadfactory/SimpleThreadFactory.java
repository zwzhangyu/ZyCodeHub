package com.example.demo.ThreadPool.threadfactory;


import java.util.concurrent.ThreadFactory;

/***
 *
 *   构建自定义的线程工厂
 * @author ZhangYu
 * @date 2021/9/9
 */
public class SimpleThreadFactory  implements ThreadFactory {

    /**
     * 线程池名称
     */
    private final  String poolName;

    public SimpleThreadFactory(String poolName) {
        this.poolName = poolName;
    }


    /**
     * 构造一个新的Thread 。 实现也可以初始化优先级，名称，守护进程状态， ThreadGroup等
     */
    @Override
    public Thread newThread(Runnable r) {
        return new  MyThread(r,poolName);
    }



}



