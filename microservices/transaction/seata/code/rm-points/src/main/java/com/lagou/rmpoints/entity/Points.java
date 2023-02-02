package com.lagou.rmpoints.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Points {

    private Integer memberId;
    private Integer points;

    public Points(){

    }

    public Points(Integer memberId, Integer points) {
        this.memberId = memberId;
        this.points = points;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
