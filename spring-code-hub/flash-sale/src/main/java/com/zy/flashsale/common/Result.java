package com.zy.flashsale.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private boolean success = false;
    private String message = null;

    public Result() {
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
