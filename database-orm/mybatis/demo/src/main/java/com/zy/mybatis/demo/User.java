package com.zy.mybatis.demo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class User {

    private Integer id;

    private Integer age;

    private String name;

    private String password;

    private Date birthday;

    private Date createTime;

    private BigDecimal salary1;

    private double salary2;

    @Tolerate
    User() {
    }
}
