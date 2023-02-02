package com.zy.design_pattern.strategy.example2;

public class Client {


    public static void main(String[] args) {
        ContextFactory contextFactory = new ContextFactory("A");
        contextFactory.contextInterface();
    }
}
