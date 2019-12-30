package com.yjt.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

@Slf4j
@Service("redisLock")
public class RedisLockImpl implements RedisLock {

	@Resource
    private RedisTemplate<String, Object> redisTemplate;

    private ThreadLocal<String> lockFlag = new ThreadLocal<String>();

    public static final String UNLOCK_LUA;
    
    public static final String orderLock = "INVENTORY_LOCK_ORDER_NO_";

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

    @Override
    public boolean setLock(String key, long expire) {
        try {
            RedisCallback<String> callback = (connection) -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();

                String uuid = UUID.randomUUID().toString();
                lockFlag.set(uuid);
                return commands.set(key, uuid, "NX", "PX", expire);
            };
            String result = redisTemplate.execute(callback);// OK
            return !StringUtils.isEmpty(result);
        } catch (Exception e) {
            log.error("设置redis锁异常,{},{}", key, e);
        }
        return false;
    }

    @Override
    public boolean releaseLock(String key) {
        try {
            List<String> keys = new ArrayList<>();
            keys.add(key);
            List<String> args = new ArrayList<>();
            args.add(lockFlag.get());
            RedisCallback<Long> callback = (connection) -> {
                Object nativeConnection = connection.getNativeConnection();
                if (nativeConnection instanceof JedisCluster) {
                    return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
                } else if (nativeConnection instanceof Jedis) {
                    return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }
                return 0L;
            };
            Long result = redisTemplate.execute(callback);
            return result != null && result > 0;
        } catch (Exception e) {
            log.error("释放redis锁异常：{},{}", key, e);
        } finally {
            lockFlag.remove();
        }
        return false;
    }

    @Override
    public boolean setLock(String key) {
        return setLock(key, 1000 * 60 * 10);// 10分钟
    }

    @Override
    public boolean tryLockBySet(String key, long expire, int tryCount) {

        int curIndex = 1;
        while (true) {
            curIndex++;
            boolean isLock = setLock(key, expire);// 最长锁10分钟
            if (isLock)
                break;
            if (curIndex > tryCount) {
                log.warn("{}获取全局锁失败，重试{}次，结束，等待下一次", key, curIndex);
                return false;
            }

            log.debug("{}获取全局锁失败，休息5秒在次尝试", key);
            try {
                Thread.sleep(1000 * 3);
            } catch (InterruptedException e) {
                log.error("休眠异常：{}", e);
            } // 休息5秒在次尝试

        }
        return true;

    }

    @Override
    public boolean tryLockBySet(String key) {
        return tryLockBySet(key, 1000 * 60 * 10, 10);
    }

    @Override
    public boolean setIfAbsent(String key, String value, long expire) {
        try {
            RedisCallback<String> callback = (connection) -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.set(key, value, "NX", "PX", expire);
            };
            String result = redisTemplate.execute(callback);// OK
            return !StringUtils.isEmpty(result);
        } catch (Exception e) {
            log.error("设置redis锁异常,{},{}", key, e);
        }
        return false;
    }

    @Override
    public Set<String> spop(String key, int count) {
        try {
            RedisCallback<Set<String>> callback = (connection) -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                Set<String> spop = commands.spop(key, count);
                return spop;
            };
            return redisTemplate.execute(callback);// OK
        } catch (Exception e) {
            log.error("spop异常, {},{}", key, e);
        }
        return null;

    }

}
