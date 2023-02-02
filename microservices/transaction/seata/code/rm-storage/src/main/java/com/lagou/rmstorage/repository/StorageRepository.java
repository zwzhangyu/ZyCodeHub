package com.lagou.rmstorage.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.rmstorage.entity.Storage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends BaseMapper<Storage> {


    @Select("SELECT * FROM storage a where a.goods_id=#{id} ")
    Storage getQuantityByGoodsId(@Param(("id")) String id);


}
