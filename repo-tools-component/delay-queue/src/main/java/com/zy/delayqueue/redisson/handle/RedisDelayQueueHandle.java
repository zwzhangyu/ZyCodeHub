package com.zy.delayqueue.redisson.handle;

/**
 * 延迟队列执行器
 * Created by LPB on 2021/04/20.
 */
public interface RedisDelayQueueHandle<T> {
 
	void execute(T t);
 
}