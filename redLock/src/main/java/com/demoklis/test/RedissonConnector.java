package com.demoklis.test;

import javax.annotation.PostConstruct;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

/**
 * 获取redissonClient连接类
 * @author demoklis
 *
 */
@Component
public class RedissonConnector {
	RedissonClient redisson;
	@PostConstruct
	public void init() {
		redisson = Redisson.create();
	}
	
	public RedissonClient getClient() {
		return redisson;
	}
}
