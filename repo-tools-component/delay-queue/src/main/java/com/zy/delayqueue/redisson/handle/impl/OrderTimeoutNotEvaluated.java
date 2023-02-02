package com.zy.delayqueue.redisson.handle.impl;

import com.zy.delayqueue.redisson.handle.RedisDelayQueueHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 订单超时未评价处理类
 * Created by LPB on 2021/04/20.
 */
@Component
@Slf4j
public class OrderTimeoutNotEvaluated implements RedisDelayQueueHandle<Map> {
	@Override
	public void execute(Map map) {
		// TODO 订单超时未评价，系统默认好评处理业务...
		Long now = System.currentTimeMillis();
		Long timestamp = Long.valueOf(String.valueOf(map.get("timestamp")));
		Long delayTime = now - timestamp;
		Long random = Long.valueOf(String.valueOf(map.get("random")));
		Long diffTime = delayTime - random * 1000;
		log.info("(OrderTimeoutNotEvaluated) orderId：{}, 预计延迟时间：{} 秒，实际延迟时间：{} 毫秒，相差：{} 毫秒",
				map.get("orderId"), random, delayTime, diffTime);
	}
}