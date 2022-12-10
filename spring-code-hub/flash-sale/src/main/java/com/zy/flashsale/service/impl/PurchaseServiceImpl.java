package com.zy.flashsale.service.impl;

import com.zy.flashsale.mapper.ProductMapper;
import com.zy.flashsale.mapper.PurchaseRecordMapper;
import com.zy.flashsale.pojo.Product;
import com.zy.flashsale.pojo.PurchaseRecord;
import com.zy.flashsale.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private ProductMapper productMapper = null;
    @Autowired
    private PurchaseRecordMapper purchaseRecordMapper = null;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean purchase(Long userId, Long productId, int quantity) {
        // 开始时间
        long startTime = System.currentTimeMillis();
        while (true) {
            // 结束时间
            long endTime = System.currentTimeMillis();
            if ((endTime - startTime) > 100) {
                // 如果间隔超过100毫秒，则返回抢购失败
                log.warn("抢购重试失败");
                return false;
            }
            // 获取产品
            Product product = productMapper.getProduct(productId);
            // 比较库存和购买数量
            if (product.getStock() < quantity) {
                // 库存不足
                log.warn("库存不足");
                return false;
            }
            // 获取当前版本号
            int version = product.getVersion();
            // 扣减库存
            int productWithVersion = productMapper.decreaseProductWithVersion(productId, quantity, version);
            if (productWithVersion == 0) {
                // 当前获取失败，继续尝试获取
                continue;
            }
            // 初始化购买记录
            PurchaseRecord pr = this.initPurchaseRecord(userId, product, quantity);
            // 插入购买记录
            purchaseRecordMapper.insertPurchaseRecord(pr);
            return true;
        }
    }

    // 初始化购买信息
    private PurchaseRecord initPurchaseRecord(
            Long userId, Product product, int quantity) {
        PurchaseRecord pr = new PurchaseRecord();
        pr.setNote("购买日志，时间：" + System.currentTimeMillis());
        pr.setPrice(product.getPrice());
        pr.setProductId(product.getId());
        pr.setQuantity(quantity);
        double sum = product.getPrice() * quantity;
        pr.setSum(sum);
        pr.setUserId(userId);
        return pr;
    }


    @Override
    public boolean purchaseByRedis(Long userId, Long productId, int quantity) {
        // 购买时间
        Long purchaseDate = System.currentTimeMillis();
        Jedis jedis = null;
        try {
            // 获取原始连接
            jedis = (Jedis) stringRedisTemplate
                    .getConnectionFactory().getConnection().getNativeConnection();
            // 如果没有加载过，则先将脚本加载到Redis服务器，让其返回sha1
            if (sha1 == null) {
                sha1 = jedis.scriptLoad(purchaseScript);
            }
            // 执行脚本，返回结果
            Object res = jedis.evalsha(sha1, 2, PRODUCT_SCHEDULE_SET,
                    PURCHASE_PRODUCT_LIST, userId + "", productId + "",
                    quantity + "", purchaseDate + "");
            Long result = (Long) res;
            return result == 1;
        } finally {
            // 关闭jedis连接
            if (jedis != null && jedis.isConnected()) {
                jedis.close();
            }
        }
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate = null;


    String purchaseScript =
            // 先将产品编号保存到集合中
            " redis.call('sadd', KEYS[1], ARGV[2]) \n"
                    // 购买列表
                    + "local productPurchaseList = KEYS[2]..ARGV[2] \n"
                    // 用户编号
                    + "local userId = ARGV[1] \n"
                    // 产品键
                    + "local product = 'product_'..ARGV[2] \n"
                    // 购买数量
                    + "local quantity = tonumber(ARGV[3]) \n"
                    // 当前库存
                    + "local stock = tonumber(redis.call('hget', product, 'stock')) \n"
                    // 价格
                    + "local price = tonumber(redis.call('hget', product, 'price')) \n"
                    // 购买时间
                    + "local purchase_date = ARGV[4] \n"
                    // 库存不足，返回0
                    + "if stock < quantity then return 0 end \n"
                    // 减库存
                    + "stock = stock - quantity \n"
                    + "redis.call('hset', product, 'stock', tostring(stock)) \n"
                    // 计算价格
                    + "local sum = price * quantity \n"
                    // 合并购买记录数据
                    + "local purchaseRecord = userId..','..quantity..','"
                    + "..sum..','..price..','..purchase_date \n"
                    //将购买记录保存到list里
                    + "redis.call('rpush', productPurchaseList, purchaseRecord) \n"
                    // 返回成功
                    + "return 1 \n";
    // Redis购买记录集合前缀
    private static final String PURCHASE_PRODUCT_LIST = "purchase_list_";
    // 抢购商品集合
    private static final String PRODUCT_SCHEDULE_SET = "product_schedule_set";
    // 32位SHA1编码，第一次执行的时候先让Redis进行缓存脚本返回
    private String sha1 = null;

    @Override
// 当运行方法启用新的独立事务运行
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean dealRedisPurchase(List<PurchaseRecord> prpList) {
        for (PurchaseRecord prp : prpList) {
            purchaseRecordMapper.insertPurchaseRecord(prp);
            productMapper.decreaseProduct(prp.getProductId(), prp.getQuantity());
        }
        return true;
    }
}