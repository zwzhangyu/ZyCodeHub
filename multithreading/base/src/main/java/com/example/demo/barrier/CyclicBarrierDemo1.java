package com.example.demo.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo1 {


    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            new Thread(new MyTask(cyclicBarrier)).start();
        }
    }

    static  class  MyTask implements  Runnable{
        private CyclicBarrier cyclicBarrier;

        public MyTask( CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId()+"开始运行 ");
            try {
                Thread.sleep((long) (Math.random()*2000));
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getId()+"集结报道");
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
