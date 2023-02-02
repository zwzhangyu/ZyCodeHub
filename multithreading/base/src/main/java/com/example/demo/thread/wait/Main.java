package com.example.demo.thread.wait;

public class Main {

    private synchronized void count(){
        for (int i = 0; i < 10; i++) {
            if(i == 5){
                try {
                    System.out.println("进入 wait 状态...");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(i);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        new Thread(main::count).start();
        Thread.sleep(2000);
        synchronized (main){
            System.out.println("唤醒线程...");
            main.notifyAll();
        }
    }
}
