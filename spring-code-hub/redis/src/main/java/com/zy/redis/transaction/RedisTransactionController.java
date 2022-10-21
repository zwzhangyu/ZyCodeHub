package com.zy.redis.transaction;

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
 * 测试Redis事务特性
 *
 * @author zhangyu
 * @date 2022/10/21
 **/
@RequestMapping("/transaction")
@RestController
public class RedisTransactionController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * redis事务简单测试
     */
    @GetMapping("/exec")
    public ResponseEntity exec() {
        // 添加一条测试基础数据
        redisTemplate.opsForValue().set("key1", "张三");
        // 使用SessionCallback保持执行的原子性
        List list = (List) redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                // 设置要监控的key,注意这里使用的是当前接口的RedisOperations对象
                redisOperations.watch("key1");
                // 开启事务，在 exec 命令执行前，全部都只是进入队列
                redisOperations.multi();
                // 事务内的一些代码
                redisOperations.opsForValue().set("key2", "Pete2");
                redisOperations.opsForValue().set("key3", "Pete3");
                // 执行 exec 命令，将先判别 key1 是否在监控后被修改过，如果是则不执行事务，否则就执行事务
                return redisOperations.exec();
            }
        });
        return ResponseEntity.ok(list);
    }


    /**
     * redis事务测试异常
     */
    @GetMapping("/testException")
    public ResponseEntity testException() {
        redisTemplate.opsForValue().set("key1", "张三");
        List list = (List) redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.watch("key1");
                redisOperations.multi();
                redisOperations.opsForValue().set("key2", "Pete2");
                // 对一个字符串数据执行增加操作，当前操作会失败
                redisOperations.opsForValue().increment("key1", 1);
                redisOperations.opsForValue().set("key3", "Pete3");
                return redisOperations.exec();
            }
        });
        return ResponseEntity.ok(list);
    }
}
