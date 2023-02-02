package com.example.demo.future;

import java.util.Set;
import java.util.concurrent.*;

public class CountDownLatchExample {

    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchExample countDownLatchExample = new CountDownLatchExample();
        Set<Integer> queryResult = countDownLatchExample.getQueryResult();
        System.out.println(queryResult);
    }

    /**
     * 获取查询结果
     */
    public Set<Integer> getQueryResult() throws InterruptedException {
        final  Set<Integer>   queryResult=new CopyOnWriteArraySet<>();
        final CountDownLatch latch = new CountDownLatch(3);
        THREAD_POOL.submit(new  PriceSearchTask(queryResult,latch));
        THREAD_POOL.submit(new  PriceSearchTask(queryResult,latch));
        THREAD_POOL.submit(new  PriceSearchTask(queryResult,latch));
        //等待3秒后返回
        latch.await(3, TimeUnit.SECONDS);
        return  queryResult;
    }

    static  class  PriceSearchTask implements Runnable{
        private final Set<Integer>  result;
        private final CountDownLatch latch;

        public PriceSearchTask(Set<Integer> result, CountDownLatch latch) {
            this.result = result;
            this.latch = latch;
        }
        @Override
        public void run() {
            int  price=0;
            try {
                Thread.sleep((long) (Math.random()*4000));
                price= (int) (Math.random()*4000);
                System.out.println(Thread.currentThread().getId()+"任务执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result.add(price);
            latch.countDown();
        }
    }
}
