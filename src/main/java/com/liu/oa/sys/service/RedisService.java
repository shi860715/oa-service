package com.liu.oa.sys.service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service("redisService")
public class RedisService {
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	
	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
	    for (String key : keys) {
	    remove(key);
	    }
	}
	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(final String pattern) {
	    Set<String> keys = redisTemplate.keys(pattern);
	    if (keys.size() > 0)
	    redisTemplate.delete(keys);
	}
	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final String key) {
	    if (exists(key)) {
	    redisTemplate.delete(key);
	    }
	}
	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
	    return redisTemplate.hasKey(key);
	}
	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public String get(final String key) {
	    String result = null;
	  
	    result =  redisTemplate.opsForValue().get(key);
	  
	    if(result==null){
	        return null;
	    }
	    return result;
	}
	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public  boolean set(final String key, String value)throws Exception {
	   boolean result = false;
	   redisTemplate.opsForValue().set(key, value);;
	   result=true;
	   return result;
	}
	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, String value, Long expireTime)throws Exception {
	    boolean result = false;
	   
	    redisTemplate.opsForValue().set(key, value,expireTime, TimeUnit.SECONDS);
	    result=true;
	    
	    return result;
	    }

	   
	
	
	
	
	
	

}
