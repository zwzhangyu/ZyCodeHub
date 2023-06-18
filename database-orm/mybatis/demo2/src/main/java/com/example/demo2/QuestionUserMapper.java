package com.example.demo2;

import com.example.demo2.QuestionUser;

public interface QuestionUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionUser record);

    int insertSelective(QuestionUser record);

    QuestionUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionUser record);

    int updateByPrimaryKey(QuestionUser record);
}