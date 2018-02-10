package com.avatarcn.user.utils;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * Created by z1ven on 2017/10/13 10:31
 */
public class HashRedisUtils<H, HK, HV> {

    private BoundHashOperations<H, HK, HV> hashOperations;

    public HashRedisUtils(RedisTemplate redisTemplate, H h) {
        hashOperations = redisTemplate.boundHashOps(h);
    }

    public void put(HK hk, HV hv) {
        hashOperations.put(hk, hv);
    }

    public HV get(HK hk) {
        return hashOperations.get(hk);
    }

    public void remove(HK hk) {
        hashOperations.delete(hk);
    }

    public void putIfAbsent(HK hk, HV hv) {
        hashOperations.putIfAbsent(hk, hv);
    }

    public Set<HK> keys() {
        return hashOperations.keys();
    }
}
