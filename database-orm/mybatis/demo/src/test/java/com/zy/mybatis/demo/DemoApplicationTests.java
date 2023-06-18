package com.zy.mybatis.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        userMapper.insert(User.builder().age(18).name("沉默二沉默王二王二").password("123456").createTime(new Date()).birthday(new Date()).salary1(new BigDecimal("1212.45")).salary2(3232.3D).build());
    }


    @Test
    void testbatchInsert() {
        List<User> userList = new ArrayList<>();
        userList.add(User.builder().age(18).name("沉默沉默王二沉默王二沉默王二沉默王二王二").password("1").createTime(new Date()).birthday(new Date()).salary1(new BigDecimal("1212.45")).salary2(3232.3D).build());
        userList.add(User.builder().age(18).name("沉默沉默王二沉默王二沉默王二沉默王二王二").password("3").createTime(new Date()).birthday(new Date()).salary1(new BigDecimal("1212.45")).salary2(3232.3D).build());
        userList.add(User.builder().age(18).name("沉默沉默王二沉默王二沉默王二沉默王二王二").password("2").createTime(new Date()).birthday(new Date()).salary1(new BigDecimal("1212.45")).salary2(3232.3D).build());
        userMapper.batchInsert(userList);
    }

    @Test
    public void testQuery() {
        List<User> all = userMapper.getAll();
        System.out.println(all);
    }

    @Test
    public void testQuery2() {
        List<User> all = userMapper.getAllJoin("Alice","1");
        System.out.println(all);
    }



    @Test
    public void testQueryMap() {
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("userId", "2");
        map.put("createdate", "createdate");
        User user = userMapper.getOneByMap(map);
        System.out.println(user);
    }

    @Test
    void testUpdate() {
        User one = userMapper.getOne(1);
        one.setPassword("沉默沉默王二沉默王二沉默王二沉默王二王二");
        userMapper.update(one);
    }

    @Test
    void testDelete() {
        userMapper.delete(1);

    }
}
