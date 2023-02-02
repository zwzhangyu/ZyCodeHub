package com.zy.utils.domain;

import lombok.Data;

@Data
public class User  extends  BaseDomain{

    private String uuid;

    private String name;
}
