package com.zy.flashsale.service.impl;

import com.zy.flashsale.pojo.PurchaseRecord;
import com.zy.flashsale.service.PurchaseService;
import com.zy.flashsale.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;

    @Autowired
    private PurchaseService purchaseService = null;

    private static final String PRODUCT_SCHEDULE_SET = "product_schedule_set";

    private static final String PURCHASE_PRODUCT_LIST = "purchase_list_";

    /**
     * 每次取出1000条，避免一次取出消耗太多内存
     */
    private static final int ONE_TIME_SIZE = 1000;


    @Override
    // 每天凌晨1点钟开始执行任务
    @Scheduled(cron = "0 0 1 * * ?")
    // 下面是用于测试的配置，每分钟执行一次任务
    // @Scheduled(fixedRate = 1000 * 60)
    public void purchaseTask() {
        System.out.println("定时任务开始......");
        Set<String> productIdList
                = stringRedisTemplate.opsForSet().members(PRODUCT_SCHEDULE_SET);
        List<PurchaseRecord> prpList = new ArrayList<>();
        assert productIdList != null;
        for (String productIdStr : productIdList) {
            // 获取产品ID
            Long productId = Long.parseLong(productIdStr);
            // 获取产品对应的购买记录redis的key
            String purchaseKey = PURCHASE_PRODUCT_LIST + productId;
            // 获取保存在redis中的购买记录
            BoundListOperations<String, String> ops = stringRedisTemplate.boundListOps(purchaseKey);
            // 计算记录数
            long size = stringRedisTemplate.opsForList().size(purchaseKey);
            long times = size % ONE_TIME_SIZE == 0 ? size / ONE_TIME_SIZE : size / ONE_TIME_SIZE + 1;
            for (int i = 0; i < times; i++) {
                // 获取至多TIME_SIZE个抢红包信息
                List<String> prList;
                if (i == 0) {
                    prList = ops.range(i * ONE_TIME_SIZE,
                            (i + 1) * ONE_TIME_SIZE);
                } else {
                    prList = ops.range(i * ONE_TIME_SIZE + 1,
                            (i + 1) * ONE_TIME_SIZE);
                }
                for (String prStr : prList) {
                    PurchaseRecord prp
                            = this.createPurchaseRecord(productId, prStr);
                    prpList.add(prp);
                }
                try {
                    // 该方法采用新建事务的方式，不会导致全局事务回滚
                    purchaseService.dealRedisPurchase(prpList);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                // 清除列表为空，等待重新写入数据
                prpList.clear();
            }
            // 删除购买列表
            stringRedisTemplate.delete(purchaseKey);
            // 从商品集合中删除商品
            stringRedisTemplate.opsForSet()
                    .remove(PRODUCT_SCHEDULE_SET, productIdStr);
        }
        System.out.println("定时任务结束......");
    }

    private PurchaseRecord createPurchaseRecord(
            Long productId, String prStr) {
        String[] arr = prStr.split(",");
        Long userId = Long.parseLong(arr[0]);
        int quantity = Integer.parseInt(arr[1]);
        double sum = Double.parseDouble(arr[2]);
        double price = Double.parseDouble(arr[3]);
        long time = Long.parseLong(arr[4]);
        Timestamp purchaseTime = new Timestamp(time);
        PurchaseRecord pr = new PurchaseRecord();
        pr.setProductId(productId);
        pr.setPurchaseTime(purchaseTime);
        pr.setPrice(price);
        pr.setQuantity(quantity);
        pr.setSum(sum);
        pr.setUserId(userId);
        pr.setNote("购买日志，时间：" + purchaseTime.getTime());
        return pr;
    }
}