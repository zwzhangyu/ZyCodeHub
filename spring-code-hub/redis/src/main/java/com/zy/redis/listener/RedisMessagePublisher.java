package com.zy.redis.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息的发布者
 *
 * @author zhangyu
 * @date 2022/10/27
 **/
@RestController
@RequestMapping("/rmp")
public class RedisMessagePublisher {


    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/publish")
    public ResponseEntity publish(String msg) {
        redisTemplate.convertAndSend(RedisMessageConstant.TOPIC, msg);
        return ResponseEntity.ok("success");

    }

}
