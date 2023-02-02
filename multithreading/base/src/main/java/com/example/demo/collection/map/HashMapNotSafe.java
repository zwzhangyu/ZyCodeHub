package com.example.demo.collection.map;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class HashMapNotSafe {

    public static void main(String[] args) {



        Map<Integer, String> map = new HashMap<>();
        Integer targetKey=8;
        String targetValue="test";
        map.put(targetKey,targetValue);
        new Thread(()->{
            IntStream.range(1,1000).forEach(p->{
                   map.put(p,targetValue);
            });
        }).start();
        while (true){
           if (null == map.get(targetKey)) {
               throw  new RuntimeException("未查询到TargetKey");
           }
        }
    }
}
