package com.example.demo.thread.safety;


/***
 *  多线程访问共享变量
 * @author ZhangYu
 * @date 2021/10/3
 */
public class Example1 {

    private static volatile  int count;

    static class MyThread implements  Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                count++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread thread1 = new Thread(myThread);
        Thread thread2 = new Thread(myThread);
        thread1.start();
        thread2.start();
        //避免主线程先执行结束
        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}
