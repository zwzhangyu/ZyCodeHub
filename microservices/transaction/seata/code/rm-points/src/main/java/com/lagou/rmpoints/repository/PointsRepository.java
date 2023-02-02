package com.lagou.rmpoints.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.rmpoints.entity.Points;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsRepository extends BaseMapper<Points> {

}
