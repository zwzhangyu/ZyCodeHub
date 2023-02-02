package com.example.demo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

    static  AtomicInteger atomicInteger=new AtomicInteger(1);

    public static void main(String[] args) {
        atomicInteger.getAndAdd(1);
    }
}
