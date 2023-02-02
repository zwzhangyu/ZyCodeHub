package com.example.demo.atomic;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

public class LongAccumulatorDemo {

    public static void main(String[] args) throws InterruptedException {
        LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(1,10).forEach(
                p->executorService.execute(()->longAccumulator.accumulate(p))
        );
        Thread.sleep(2000);
        System.out.println(longAccumulator.getThenReset());
    }
}
