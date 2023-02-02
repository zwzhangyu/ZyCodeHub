package com.example.demo.thread.safety;


/***
 *
 *  死锁问题
 * @author ZhangYu
 * @date 2021/10/3
 */
public class DeadLockDemo {

    /**  锁A **/
    private  final  static Object LOCKA =new Object();
    /**  锁B **/
    private  final  static  Object LOCKB =new Object();


    static  class  ThreadA implements  Runnable{
        @Override
        public void run() {
             synchronized (LOCKA){
                 System.out.println(Thread.currentThread().getId()+"==>获取资源A");
                 try {
                     Thread.sleep(500);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 synchronized (LOCKB){
                     System.out.println(Thread.currentThread().getId()+"==>获取资源B");
                 }
             }
        }
    }


    static  class  ThreadB implements  Runnable{
        @Override
        public void run() {
            synchronized (LOCKB){
                System.out.println(Thread.currentThread().getId()+"==>获取资源B");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCKA){
                    System.out.println(Thread.currentThread().getId()+"==>获取资源A");
                }
            }
        }
    }

    public static void main(String[] args) {
         new Thread(new ThreadA()).start();
         new Thread(new ThreadB()).start();
    }
}
