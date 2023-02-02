package com.example.mdc.example1;


import java.math.BigDecimal;

/**
 * 转账服务
 * @author  zhangyu
 */
public  abstract  class TransferService {
    public boolean transfer(BigDecimal amount) {
        beforeTransfer(amount);
        //调用第三方转账接口
        afterTransfer(amount,true);
        return true;
    }

    abstract protected void beforeTransfer(BigDecimal amount);

    abstract protected void afterTransfer(BigDecimal amount, boolean outcome);
}
