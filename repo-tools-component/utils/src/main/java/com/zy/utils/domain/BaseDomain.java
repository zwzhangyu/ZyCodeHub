package com.zy.utils.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDomain {

    private Long  id;

    private Date createTime;

    private Date updateTime;



}
