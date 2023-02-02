package com.example.mdc.example1;

import org.apache.log4j.MDC;

public class Log4JRunnable implements Runnable {
    private Transfer tx;
    private  TransferService transferService=new LogTransferService();

    public Log4JRunnable(Transfer tx) {
        this.tx = tx;
    }

    @Override
    public void run() {
        MDC.put("transaction.id", tx.getTransactionId());
        MDC.put("transaction.owner", tx.getSender());
        transferService.transfer(tx.getAmount());
        MDC.clear();
    }
}