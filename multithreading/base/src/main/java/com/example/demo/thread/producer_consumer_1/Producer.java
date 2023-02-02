package com.example.demo.thread.producer_consumer_1;

public class Producer  implements  Runnable  {

    private  Container container;

    public Producer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println("生产数据===="+i);
            container.put(i);
        }
    }
}
