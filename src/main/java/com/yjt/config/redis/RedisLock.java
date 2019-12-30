package com.yjt.config.redis;

import java.util.Set;

/**
 * redis锁
 * @author wangz
 * 时间： 2018年8月27日
 */
public interface RedisLock {

	/**
	 * 获取成功后返回true，获取失败返回false
	 * @param key
	 * @param expire 单位毫秒 默认10分钟
	 * @return true or false
	 */
	public boolean setLock(String key, long expire);
	
	/**
	 * 获取锁，默认10分钟
	 * @param key
	 * @return true 成功获取， false 获取失败
	 */
	public boolean setLock(String key);
	
	/**
	 * 释放锁
	 * @param key
	 * @return
	 */
	public boolean releaseLock(String key);
	
	
	/**
	 * 重试获取锁
	 * @param key
	 * @param expire
	 * @param tryCount 3次 
	 * @return true 成功获取， false 获取失败
	 */
	boolean tryLockBySet(String key, long expire, int tryCount);
	
	
	/**
	 * 重试获取锁 
	 * 如果失败重试3次
	 * 获取到后锁定10分钟
	 * @param key
	 * @return true 成功获取， false 获取失败
	 */
	boolean tryLockBySet(String key);
	

	Set<String> spop(String key, int count);


	boolean setIfAbsent(String key, String value, long expire);
}
