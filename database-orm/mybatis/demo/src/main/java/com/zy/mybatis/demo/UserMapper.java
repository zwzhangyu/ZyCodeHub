package com.zy.mybatis.demo;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {


    @Select("SELECT * FROM us1er")
    List<User> getAll();

    @Select("SELECT\n" +
            "\tt1.*,\n" +
            "\tt2.* \n" +
            "FROM\n" +
            "\tUSER t1\n" +
            "\tLEFT JOIN t_order_info t2 ON t1.id = t2.user_id \n" +
            "\tAND t1.NAME = #{nam3e} \n" +
            "\tAND t2.company_id = #{comp3anyId}")
    List<User> getAllJoin(String name,String companyId);


    @Select("SELECT * FROM user WHERE id = #{id}")
    User getOne(Integer id);

    @Select("SELECT * FROM user WHERE id = #{params.userId}")
    User getOneByMap(@Param("params") Map<String,Object> map);


    @Insert("INSERT INTO user(name,password,age,birthday,create_time,salary1,salary2) " +
            "VALUES(#{name}, #{password}, #{age},#{birthday},#{createTime},#{salary1},#{salary2})")
    void insert(User user);





    @Insert({
            "<script>",
            "INSERT INTO user (age, name, password, birthday, create_time, salary1, salary2) VALUES ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "( #{item.age}, #{item.name}, #{item.password}, #{item.birthday}, #{item.createTime}, #{item.salary1}, #{item.salary2})",
            "</foreach>",
            "</script>"
    })
    void batchInsert(List<User> userList);

    @Update("UPDATE user SET name=#{name},password=#{password},age=#{age} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM us1er WHERE id =#{id}")
    void delete(Integer id);
}
