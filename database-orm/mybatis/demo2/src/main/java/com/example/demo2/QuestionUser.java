package com.example.demo2;

import lombok.Data;

import java.util.Date;

@Data
public class QuestionUser {
    /**
     */
    private Integer id;

    /**
     * 问卷id
     */
    private Integer infoId;

    /**
     * 提交序号
     */
    private Integer sort;

    /**
     * 0.无效，1.有效
     */
    private Integer useable;

    /**
     * 状态 0.未填写 1.已填写
     */
    private Integer status;

    /**
     * 填写人id
     */
    private Integer writeId;

    /**
     * 创建人id
     */
    private Integer createUser;

    /**
     */
    private Date createTime;

    /**
     * 更新者id
     */
    private Integer updateUser;

    /**
     */
    private Date updateTime;

    /**
     * 逻辑删除 1 表示删除，0 表示未删除
     */
    private Integer isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getUseable() {
        return useable;
    }

    public void setUseable(Integer useable) {
        this.useable = useable;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWriteId() {
        return writeId;
    }

    public void setWriteId(Integer writeId) {
        this.writeId = writeId;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}