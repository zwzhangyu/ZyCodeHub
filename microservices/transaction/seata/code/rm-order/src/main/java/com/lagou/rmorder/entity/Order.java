package com.lagou.rmorder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@TableName(value = "t_order")
public class Order {


    private Integer order_id; //订单编号
    private Integer memberId; //会员编号

    private Integer goodsId; //商品编号
    private Integer points; //新增积分
    private Integer quantity; //销售数量

    public Order() {
    }

    public Order(Integer memberId, Integer goodsId, Integer points, Integer quantity) {
        this.memberId = memberId;
        this.goodsId = goodsId;
        this.points = points;
        this.quantity = quantity;
    }
}
