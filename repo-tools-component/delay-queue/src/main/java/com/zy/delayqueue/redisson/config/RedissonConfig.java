package com.zy.delayqueue.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by LPB on 2020/04/20.
 */
@Configuration
public class RedissonConfig {
 
	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.database}")
	private int database;
	@Value("${spring.redis.password}")
	private String password;

	@Bean
	public RedissonClient redissonClient() {
		Config config = new Config();
		config.useSingleServer()
			.setAddress("redis://" + host + ":" + port)
			.setDatabase(database)
			.setPassword(password);
		return Redisson.create(config);
	}

}