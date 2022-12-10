package com.zy.flashsale.mapper;

import com.zy.flashsale.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {

    // 获取产品
    public Product getProduct(Long id);

    // 减库存，而@Param标明MyBatis参数传递给后台
    public int decreaseProduct(@Param("id") Long id,
                               @Param("quantity") int quantity);
    /**
     * 减库存，使用乐观锁
     */
    int decreaseProductWithVersion(@Param("id") Long id,
                                   @Param("quantity") int quantity,
                                   @Param("version") int version);
}


