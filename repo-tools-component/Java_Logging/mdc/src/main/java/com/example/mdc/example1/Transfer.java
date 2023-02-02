package com.example.mdc.example1;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 转账类
 */
@Data
public class Transfer {
    /**
     * 转账事务唯一标识
     */
    private String transactionId;
    /**
     * 收款方
     */
    private String sender;
    /**
     * 转账金额
     */
    private BigDecimal amount;
}
