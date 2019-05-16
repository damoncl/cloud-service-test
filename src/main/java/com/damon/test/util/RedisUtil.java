package com.damon.test.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@SuppressWarnings({"unchecked", "rawtypes"})
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 写入缓存
     *
     * @param key   - 键
     * @param value - 值
     */

    public boolean set(final String key, Object value) {
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(reCreateKey(key), value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @param seconds - 单位：秒
     * @return
     */
    public boolean setex(final String key, Object value, long seconds) {
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();

            String keyNew = reCreateKey(key);
            operations.set(keyNew, value);
            redisTemplate.expire(keyNew, seconds, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据key获取到值
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(reCreateKey(key));
    }

    /**
     * 根据key获取到值
     *
     * @param key
     * @return
     */
    public <T> T get(final String key, Class clazz) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        Object value = operations.get(reCreateKey(key));
        if (value == null) {
            return null;
        }
        return (T) value;
    }

    /**
     * 删除key
     *
     * @param key - 键
     */
    public boolean remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(reCreateKey(key));
            return true;
        }
        return false;
    }

    /**
     * 根据key判断key的存在性
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(reCreateKey(key));
    }

    private String reCreateKey(String key) {
        return "service_test:" + key;
    }
}
