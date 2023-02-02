package com.example.demo.finalDemo;

public class FinalDemo {

    public static void main(String[] args) {
        String s1="ABC";
        String s2="ABC";
        System.out.println(s1==s2);
        String s3=String.valueOf("ABC");
        System.out.println(s1==s3);
        String s4=String.valueOf(new char[]{'A','B','C'});
        System.out.println(s1==s4);




    }
}
