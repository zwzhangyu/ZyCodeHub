package com.example.demo.future;

import java.util.concurrent.*;


/***
 *
 *  测试Future  cancel(mayInterruptIfRunning);方法
 * @author ZhangYu
 * @date 2021/10/19
 */
public class Example1 {

     static  class MyTask implements Callable {
         @Override
         public Object call() {
             try {
                 System.out.println("任务开始执行");
                 Thread.sleep(3000);
                 System.out.println("运行结束");
             } catch (InterruptedException e) {
                 System.out.println("检测到中断通知");
             }
             return null;
         }
     }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future future = executorService.submit(new MyTask());
        //模拟等待线程创建
        Thread.sleep(500);
        //设置中断
        System.out.println(future.cancel(true));
        executorService.shutdown();
    }

}
