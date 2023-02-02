package com.example.demo.future;

import java.util.concurrent.*;

public class FutureTaskExample {

    private final ConcurrentMap<Object, Future<String>> taskCache = new ConcurrentHashMap<Object, Future<String>>();
    private String executionTask(final String taskName)throws ExecutionException, InterruptedException {
        while (true) {
            Future<String> future = taskCache.get(taskName);
            if (future == null) {
                Callable<String> task = new Callable<String>() {
                    @Override
                    public String call() throws InterruptedException {
                        return taskName;
                    }
                };
                FutureTask<String> futureTask = new FutureTask<String>(task);
                future = taskCache.putIfAbsent(taskName, futureTask);
                if (future == null) {
                    future = futureTask;
                    futureTask.run();
                }
            }
            try {return future.get();
               } catch (CancellationException e) {
                taskCache.remove(taskName, future);
            }
        }
    }
}
