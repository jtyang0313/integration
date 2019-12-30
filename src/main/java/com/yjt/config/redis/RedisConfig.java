package com.yjt.config.redis;

import java.lang.reflect.Method;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisConfigInfo redisConfigInfo() {
        RedisConfigInfo config = new RedisConfigInfo();
        return config;
    }

    @Bean
    public JedisPoolConfig getRedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        RedisConfigInfo configInfo = redisConfigInfo();

        poolConfig.setMaxIdle(configInfo.getMaxIdle());
        poolConfig.setMinIdle(configInfo.getMinIdle());

        poolConfig.setMaxTotal(configInfo.getMaxActive());
        poolConfig.setMaxWaitMillis(configInfo.getMaxWait());

        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
        log.info("连接池配置：" + poolConfig);
        return poolConfig;
    }


    @Bean
    @Primary
    public JedisConnectionFactory getConnectionFactory() {

        RedisConfigInfo configInfo = redisConfigInfo();
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setDatabase(configInfo.getDatabase());
        configuration.setHostName(configInfo.getHost());
        RedisPassword password= RedisPassword.of(configInfo.getPassword());
        configuration.setPassword(password);
        configuration.setPort(configInfo.getPort());
        log.info("JedisConnectionFactory初始化成功" + configInfo);
        return new JedisConnectionFactory(configuration);
    }



    @Bean
    @Primary
    RedisTemplate<String,Object> objectRedisTemplate(){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(getConnectionFactory());
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        return redisTemplate;
    }


    /**
     * 重写缓存key生成策略，可根据自身业务需要进行自己的配置生成条件
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
}
