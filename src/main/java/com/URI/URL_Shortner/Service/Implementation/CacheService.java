package com.URI.URL_Shortner.Service.Implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CacheService {

    private static  final String URL_CACHE_PREFIX="URL:";
    private  static  final long TTL_HOURS=24;

    private final StringRedisTemplate redisTemplate;
    public void warmCache(String shortCode,String longUrl){
        redisTemplate.opsForValue().set(URL_CACHE_PREFIX+shortCode,longUrl,TTL_HOURS*60*60, TimeUnit.HOURS);
    }
    public String get(String shortCode){
        return redisTemplate.opsForValue().getAndDelete(URL_CACHE_PREFIX+shortCode);
    }

    public void evict(String shortCode){
        redisTemplate.delete(URL_CACHE_PREFIX+shortCode);
    }
}
