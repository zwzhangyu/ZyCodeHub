package com.zy.design_pattern.pipeline.example1;

public class Client {
    public static void main(String[] args) {
        String s = "11,22,33";
        System.out.println("原始输入 : " + s);
        StandardPipeline pipeline = new StandardPipeline();
        TailValve tail = new TailValve();
        FirstValve first = new FirstValve();
        SecondValve second = new SecondValve();
        // 设置管道尾部节点
        pipeline.setTail(tail);
        // 依次将节点加入至管道链表中
        pipeline.addValve(first);
        pipeline.addValve(second);
        // 从管道头部开始处理
        pipeline.getHead().invoke(s);
    }
}