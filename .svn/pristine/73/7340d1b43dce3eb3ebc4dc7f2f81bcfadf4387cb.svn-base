package com.yjt.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * redis配置信息
 * 
 * @author wangz 时间： 2018年9月25日
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigInfo {

	private String host;
	private int port;
	private String password;
	private int database;
	private int timeout;

//	最大空闲连接数
	@Value("${spring.redis.pool.max-idle}")
	private int maxIdle;
	@Value("${spring.redis.pool.min-idle}")
	private int minIdle;
	
	//最大连接数
	@Value("${spring.redis.pool.max-active}")
	private int maxActive;

	@Value("${spring.redis.pool.max-wait}")
	private int maxWait;
}