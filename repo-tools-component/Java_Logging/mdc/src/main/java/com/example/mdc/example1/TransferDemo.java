package com.example.mdc.example1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.MINUTES;


public class TransferDemo {

    public static void main(String[] args) {
        ExecutorService executor = new MdcAwareThreadPoolExecutor(3, 3, 0, MINUTES,
                new LinkedBlockingQueue<>(), Thread::new, new ThreadPoolExecutor.AbortPolicy());
        TransactionFactory transactionFactory = new TransactionFactory();

        for (int i = 0; i < 10; i++) {
            Transfer tx = transactionFactory.newInstance();
            Runnable task = new Log4JRunnable(tx);
            executor.submit(task);
        }

        executor.shutdown();
    }
}