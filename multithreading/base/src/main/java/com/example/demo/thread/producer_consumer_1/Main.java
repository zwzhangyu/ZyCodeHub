package com.example.demo.thread.producer_consumer_1;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Container container = new Container(5, new LinkedList<>());
        Thread pThread = new Thread(new Producer(container));
        pThread.start();
        Thread cThread = new Thread(new Consumer(container));
        cThread.start();

    }
}
