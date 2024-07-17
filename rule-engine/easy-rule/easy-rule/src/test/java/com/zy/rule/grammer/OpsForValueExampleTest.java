package com.zy.rule.grammer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public  class OpsForValueExampleTest {
     @Autowired
    RedisTemplate<String, Object> redisTemplate;


      @Test
      public  void  opsForValueTest(){
          Map valueMap = new HashMap();
          valueMap.put("valueMap:dd","map1");
          valueMap.put("valueMap:ee","map2");
          valueMap.put("valueMap:eee","map3");
          redisTemplate.opsForValue().multiSet(valueMap);

      }


}