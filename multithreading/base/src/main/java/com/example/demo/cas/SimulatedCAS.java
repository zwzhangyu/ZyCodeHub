package com.example.demo.cas;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义CAS
 */
public class SimulatedCAS  implements  Runnable {

    private  static  volatile int value=0;

    public synchronized  static  boolean compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
            return  true;
        }
        return false;
    }

    public static   int getAndIncrement(int newValue){
          int  expectedValue;
          do{
                expectedValue=value;
          }while (!compareAndSwap(expectedValue,expectedValue+newValue));
          return  value;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            getAndIncrement(1);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new SimulatedCAS());
        Thread thread2 = new Thread(new SimulatedCAS());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(value);
    }
}
