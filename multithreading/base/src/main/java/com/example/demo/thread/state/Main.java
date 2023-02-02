package com.example.demo.thread.state;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    break;
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    //手动再次中断
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });
       thread.start();
       //主线程休眠500ms，让测试线程运行一个时间段
       Thread.sleep(500);
       thread.interrupt();
    }
}
