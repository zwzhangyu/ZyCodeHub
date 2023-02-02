package com.example.demo.collection.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        new Thread(()->{
            IntStream.range(1,1000).forEach(p->{
                map.put(p,"");
            });
        }).start();
        while (true);
    }
}
