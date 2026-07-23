package com.URI.URL_Shortner.Service.Blocklist;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class BlocklistCacheService {

    private static final String CACHEPREFIX = "blocklist:";
    private final StringRedisTemplate stringRedisTemplate;


    public Boolean isBlockedCached(String domain){
        String value=stringRedisTemplate.opsForValue().get(CACHEPREFIX+domain);
        if(value==null){
            return  null;
        }
        return Boolean.parseBoolean(value);
    }

    public void cacheResult(String domain,boolean isBlocked){
        stringRedisTemplate.opsForValue().set(CACHEPREFIX+domain,
                String.valueOf(isBlocked),
                24, TimeUnit.HOURS
                );
    }

    public  void evict(String domain){
        stringRedisTemplate.delete(CACHEPREFIX+domain);
    }

}
