package com.zy.redis.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@ResponseBody
@RestController
@RequestMapping("/rt")
public class RedisTemplateController {


    // 注入 RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate = null;

    @GetMapping("/testRedis")
    public String testRedis() {
        useRedisCallback(redisTemplate);
        return "OK";
    }

    // 需要处理底层的转换规则，如果不考虑改写底层，尽量不使用它
    public void useRedisCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute((RedisCallback) rc -> {
            rc.set("key1".getBytes(), "value1".getBytes());
            rc.hSet("hash".getBytes(), "field".getBytes(), "hvalue".getBytes());
            return null;
        });
    }

    // 高级接口，比较友好，一般情况下，优先使用它
    public void useSessionCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations ro)
                    throws DataAccessException {
                ro.opsForValue().set("key1", "value1");
                ro.opsForHash().put("hash", "field", "hvalue");
                return null;
            }
        });
    }


    @GetMapping("/testValue")
    public String testValue() {
        // 存储键值
        redisTemplate.opsForValue().set("key", "value");
        // 存储map集合
        Map<String, String> map = new HashMap(2);
        map.put("key1", "value1");
        map.put("key2", "value2");
        redisTemplate.opsForHash().putAll("myHash", map);
        // 添加单个kv到哈希对象集合中
        redisTemplate.opsForHash().put("myHash", "key3", "value3");
        // 绑定散列操作的 key，这样可以连续对同一个散列数据类型进行操作
        BoundHashOperations hashOps = redisTemplate.boundHashOps("myHash");
        // 删除两个字段
        hashOps.delete("key2", "key3");
        // 新增一个字段
        hashOps.put("key4", "value4");
        return "OK";
    }


    @GetMapping("/testNodeList")
    public String testNodeList() {
        // 插入两个列表,注意它们在链表的顺序
        // 链表从左到右顺序为 v10,v8,v6,v4,v2
        redisTemplate.opsForList().leftPushAll(
                "list1", "v2", "v4", "v6", "v8", "v10");
        // 链表从左到右顺序为 v1,v2,v3,v4,v5,v6
        redisTemplate.opsForList().rightPushAll(
                "list2", "v1", "v2", "v3", "v4", "v5", "v6");
        // 绑定 list2 链表操作
        BoundListOperations listOps = redisTemplate.boundListOps("list2");
        // 从右边弹出一个成员
        Object result1 = listOps.rightPop();
        // 获取定位元素，Redis 从 0 开始计算,这里值为 v2
        Object result2 = listOps.index(1);
        // 从左边插入链表
        listOps.leftPush("v0");
        // 求链表长度
        Long size = listOps.size();
        // 求链表下标区间成员，整个链表下标范围为 0 到 size-1，这里不取最后一个元素
        List elements = listOps.range(0, size - 2);
        return "OK";
    }


    @GetMapping("/testSet")
    public String testSet() {
        // 请注意：这里 v1 重复两次，因为集合不允许重复，所以只是插入 5 个成员到集合中
        redisTemplate.opsForSet().add("set1",
                "v1", "v1", "v2", "v3", "v4", "v5");
        redisTemplate.opsForSet().add("set2", "v2", "v4", "v6", "v8");
        // 绑定 set1 集合操作
        BoundSetOperations setOps = redisTemplate.boundSetOps("set1");
        // 增加两个元素
        setOps.add("v6", "v7");
        // 删除两个元素
        setOps.remove("v1", "v7");
        // 返回所有元素
        Set set1 = setOps.members();
        // 求成员数
        Long size = setOps.size();
        // 求交集
        Set inter = setOps.intersect("set2");
        // 求交集，并且用新集合 inter 保存
        setOps.intersectAndStore("set2", "inter");
        // 求差集
        Set diff = setOps.diff("set2");
        // 求差集，并且用新集合 diff 保存
        setOps.diffAndStore("set2", "diff");
        // 求并集
        Set union = setOps.union("set2");
        // 求并集，并且用新集合 union 保存
        setOps.unionAndStore("set2", "union");
        return "OK";
    }

    @RequestMapping("/zset")
    @ResponseBody
    public Map<String, Object> testZset() {
        Set<ZSetOperations.TypedTuple<String>> typedTupleSet = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            // 分数
            double score = i * 0.1;
            // 创建一个 TypedTuple 对象，存入值和分数
            ZSetOperations.TypedTuple<String> typedTuple
                    = new DefaultTypedTuple<String>("value" + i, score);
            typedTupleSet.add(typedTuple);
        }
        // 往有序集合插入元素
        redisTemplate.opsForZSet().add("zset1", typedTupleSet);
        // 绑定 zset1 有序集合操作
        BoundZSetOperations<String, String> zsetOps
                = redisTemplate.boundZSetOps("zset1");
        // 增加一个元素
        zsetOps.add("value10", 0.26);
        Set<String> setRange = zsetOps.range(1, 6);
        // 按分数排序获取有序集合
        Set<String> setScore = zsetOps.rangeByScore(0.2, 0.6);
        // 定义值范围
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.gt("value3");// 大于 value3
        // range.gte("value3");// 大于等于 value3
        // range.lt("value8");// 小于 value8
        range.lte("value8");// 小于等于 value8
        // 按值排序，请注意这个排序是按字符串排序
        Set<String> setLex = zsetOps.rangeByLex(range);
        // 删除元素
        zsetOps.remove("value9", "value2");
        // 求分数
        Double score = zsetOps.score("value8");
        // 在下标区间下，按分数排序，同时返回 value 和 score
        Set<ZSetOperations.TypedTuple<String>> rangeSet = zsetOps.rangeWithScores(1, 6);
        // 在分数区间下，按分数排序，同时返回 value 和 score
        Set<ZSetOperations.TypedTuple<String>> scoreSet = zsetOps.rangeByScoreWithScores(1, 6);
        // 按从大到小排序
        Set<String> reverseSet = zsetOps.reverseRange(2, 8);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        return map;
    }
}
