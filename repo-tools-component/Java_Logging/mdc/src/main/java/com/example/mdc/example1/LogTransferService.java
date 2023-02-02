package com.example.mdc.example1;


import org.apache.log4j.Logger;

import java.math.BigDecimal;

public class LogTransferService extends TransferService {
    private Logger logger = Logger.getLogger(LogTransferService.class);

    @Override
    protected void beforeTransfer(BigDecimal amount) {
        logger.info("Preparing to transfer " + amount + "$.");
    }

    @Override
    protected void afterTransfer(BigDecimal amount, boolean outcome) {
        logger.info(
                "Has transfer of " + amount + "$ completed successfully ? " + outcome + ".");
    }
}