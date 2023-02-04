package com.zy.design_pattern.builder.example1;

public class Main {

    public static void main(String[] args) {
        User user = new User.Builder().name("张三").address("合肥").age(25).builder();
        System.out.println(user);
    }
}
