package com.example.demo2;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Date;

@SpringBootTest
class Demo2ApplicationTests {

    @Autowired
    private QuestionUserMapper questionUserMapper;

    @Test
    void testInsert() {

        QuestionUser user = new QuestionUser();
        user.setId(1);
        user.setInfoId(111111111);
        user.setSort(1);
        user.setUseable(1);
        user.setStatus(1);
        user.setWriteId(1);
        user.setCreateUser(1);
        user.setCreateTime(new Date());
        user.setUpdateUser(1);
        user.setUpdateTime(new Date());
        user.setIsDeleted(0);

        int result = questionUserMapper.insert(user);

    }


}
