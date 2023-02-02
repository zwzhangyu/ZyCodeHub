package com.example.demo.synchonized;

public class SynchonizedExample {

   public    void test1(){
       synchronized (this){
           System.out.println("Test method block");
       }
   }

    public  synchronized  void test2(){
            System.out.println("Test method");
    }
}
