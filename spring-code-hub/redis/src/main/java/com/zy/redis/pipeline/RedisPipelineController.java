package com.zy.redis.pipeline;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试Redis流水线
 *
 * @author zhangyu
 * @date 2022/10/21
 **/
@RestController
@RequestMapping("/pipeline")
public class RedisPipelineController {
    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/batchInsert")
    public ResponseEntity batchInsert() {
        Long start = System.currentTimeMillis();
        List list = (List) redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                for (int i = 0; i < 100000; i++) {
                    redisOperations.opsForValue().set("key" + i, "value" + i);
                }
                return null;
            }
        });
        Long end = System.currentTimeMillis();
        System.out.println("批量插入10万条数据耗时：" + (end - start) + "毫秒");
        // 725毫秒  689毫秒   797毫秒
        return ResponseEntity.ok().build();
    }

    @GetMapping("/insert")
    public ResponseEntity insert() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            redisTemplate.opsForValue().set("key" + i, "value" + i);
        }
        Long end = System.currentTimeMillis();
        System.out.println("循环插入10万条数据耗时：" + (end - start) + "毫秒");
        // 6772毫秒  7676毫秒  6780毫秒
        return ResponseEntity.ok().build();
    }
}
