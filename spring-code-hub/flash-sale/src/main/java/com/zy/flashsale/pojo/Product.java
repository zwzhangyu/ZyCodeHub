package com.zy.flashsale.pojo;


import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("product")
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 3288311147760635602L;
    private Long id;
    private String productName;
    private int stock;
    private double price;
    private int version;
    private String note;
}

