package com.example.grammer.optional;

import org.testng.annotations.Test;

import java.util.*;

public class Example1 {

    @Test
    public  void test1(){
        Optional<String> opt = Optional.of("程序新视界");
        Optional<Integer> intOpt = opt.map(String::length);
        System.out.println(intOpt.orElse(0));





    }

}
