package com.example.demo.ThreadPool.stop;

import java.util.concurrent.atomic.AtomicInteger;
/***
 *   测试线程任务
 * @author ZhangYu
 * @date 2021/10/5
 */
public class MyTask   implements  Runnable{
    private  final  static   AtomicInteger count=new AtomicInteger();

    private final String name;

    public MyTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException exception) {
            System.out.println("当前任务名称=="+name+"接收到中断异常");
        }
        System.out.println("当前任务名称=="+name+"计数=="+count.incrementAndGet());
    }
}
