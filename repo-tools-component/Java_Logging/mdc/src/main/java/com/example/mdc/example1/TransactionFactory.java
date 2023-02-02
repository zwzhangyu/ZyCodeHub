package com.example.mdc.example1;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TransactionFactory {
     private  final Random random=new Random();
    private final List<String>   senderList= Arrays.asList("Alice","Tom","Bob");
    public Transfer newInstance() {
        Transfer transfer=new Transfer();
        transfer.setSender(senderList.get(random.nextInt(2)));
        transfer.setAmount(new BigDecimal(random.nextInt(10000)+100));
        transfer.setTransactionId(UUID.randomUUID().toString());
        return  transfer;
    }

}
