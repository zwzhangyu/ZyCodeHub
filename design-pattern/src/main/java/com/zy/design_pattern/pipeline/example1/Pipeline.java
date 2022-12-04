package com.zy.design_pattern.pipeline.example1;

/**
 * 管道接口
 *
 * @author zhangyu
 * @date 2022/12/4
 **/
public interface Pipeline {

    /**
     * 获取管道中的第一个阀门节点
     */
    public Valve getHead();

    /**
     * 获取管道中的第一个尾部阀门节点
     */
    public Valve getTail();

    /**
     * 设置管道中的第一个尾部阀门节点
     */
    public void setTail(Valve v);

    /**
     * 为管道添加阀门节点
     */
    public void addValve(Valve v);
}
