package com.zy.spring.mvc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class User {
    private Long id;

    private String name;

    private String phone;

    private Date birthday;


    public User() {
    }

    public static List<User> list() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "李四2", "1212121"));
        userList.add(new User(2L, "李四3", "4343"));
        userList.add(new User(3L, "李四4", "4343"));
        return userList;
    }

    public User(Long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
}
