package com.zy.redis.lua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lua")
public class RedisLuaScriptController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/test1")
    public Map<String, Object> testLua() {
        DefaultRedisScript<String> rs = new DefaultRedisScript<String>();
        // 设置脚本
        rs.setScriptText("return 'Hello Redis'");
        // 定义返回类型。注意：如果没有这个定义，Spring 不会返回结果
        rs.setResultType(String.class);
        RedisSerializer<String> stringSerializer
                = redisTemplate.getStringSerializer();
        // 执行 Lua 脚本
        String str = (String) redisTemplate.execute(
                rs, stringSerializer, stringSerializer, null);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("str", str);
        return map;
    }

    @RequestMapping("/lua2")
    @ResponseBody
    public Map<String, Object> testLua2(String key1, String key2, String value1, String value2) {
        // 定义 Lua 脚本
        String lua = "redis.call('set', KEYS[1], ARGV[1]) \n"
                + "redis.call('set', KEYS[2], ARGV[2]) \n"
                + "local str1 = redis.call('get', KEYS[1]) \n"
                + "local str2 = redis.call('get', KEYS[2]) \n"
                + "if str1 == str2 then \n"
                + "return 1 \n"
                + "end \n"
                + "return 0 \n";
        System.out.println(lua);
        // 结果返回为 Long
        DefaultRedisScript<Long> rs = new DefaultRedisScript<Long>();
        rs.setScriptText(lua);
        rs.setResultType(Long.class);
        // 采用字符串序列化器
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        // 定义 key 参数
        List<String> keyList = new ArrayList<>();
        keyList.add(key1);
        keyList.add(key2);
        // 传递两个参数值，其中第一个序列化器是 key 的序列化器，第二个序列化器是参数的序列化器
        Long result = (Long) redisTemplate.execute(
                rs, stringSerializer, stringSerializer, keyList, value1, value2);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", result);
        return map;
    }


}
