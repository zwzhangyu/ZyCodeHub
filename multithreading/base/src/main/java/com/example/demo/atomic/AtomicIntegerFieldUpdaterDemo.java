package com.example.demo.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterDemo  implements Runnable {
    static Score math;
    static Score computer;
    private  static  AtomicIntegerFieldUpdater<Score> scoreUpdater=AtomicIntegerFieldUpdater.newUpdater(Score.class,
            "score");
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            computer.score++;
            scoreUpdater.getAndIncrement(math);
        }
    }
     static class Score {
       volatile int score;
     }
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerFieldUpdaterDemo updaterDemo = new AtomicIntegerFieldUpdaterDemo();
        math=new Score();
        computer=new Score();
        Thread thread1 = new Thread(updaterDemo);
        Thread thread2 = new Thread(updaterDemo);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(math.score);
        System.out.println(computer.score);
    }
}
