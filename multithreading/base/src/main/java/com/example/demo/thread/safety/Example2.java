package com.example.demo.thread.safety;


import java.util.HashMap;
import java.util.Map;

/***
 *  发布和初始化导致线程安全问题
 * @author ZhangYu
 * @date 2021/10/3
 */
public class Example2 {

    private Map<String, String>  map;

    public Map<String, String> getMap() {
        return map;
    }

    /**
     *  启动子线程初始化对象内部容器
     */
    public Example2() {
         new Thread(()->{
             map=new HashMap<>();
             map.put("1","Alice");
             map.put("2","Bob");
         });
    }
    public static void main(String[] args) {
        Example2 example2 = new Example2();
        System.out.println(example2.getMap().get("1"));
    }
}
