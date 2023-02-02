package com.example.demo.thread.producer_consumer_1;

public class Consumer  implements  Runnable {

    private  Container container;

    public Consumer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
       while (true){
           System.out.println("消费数据===="+container.take());
       }
    }
}
