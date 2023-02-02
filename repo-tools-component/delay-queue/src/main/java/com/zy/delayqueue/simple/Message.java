package com.zy.delayqueue.simple;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/***
 *   消息
 * @author ZhangYu
 * @date 2021/6/14
 */
public class Message implements Delayed {
    /**  消息内容  **/
    private final String body;
    /**  过期时间  **/
    private final long expireTime;

    /**
     *    初始化消息
     * @param body  消息体
     * @param expireTime  延时过期时间
     */
    public Message(String body, long expireTime) {
        this.body = body;
        this.expireTime = expireTime+System.currentTimeMillis();
    }

    /**
     * 在给定的时间单位中返回与此对象相关联的剩余延迟。
     * @param unit   时间单位
     * @return 剩余的延迟; 零或负值表示延迟已经过去
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expireTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }


    @Override
    public int compareTo(Delayed o) {
        return  (int)(this.getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS));
    }

    public  void  execute(){
        System.out.println("执行任务==>"+body);
    }
}
