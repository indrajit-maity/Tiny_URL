package com.URI.URL_Shortner.Configuration;

import io.github.bucket4j.redis.jedis.cas.JedisBasedProxyManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
public class RateLimiterRedisConfig {
    @Value("${spring.data.redis.host}")
    private String redisHost;
    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    public JedisPool jedisPool(){
        return new JedisPool(redisHost,redisPort);
    }
    @Bean
    public JedisBasedProxyManager<byte[]> proxyManager(JedisPool jedisPool){
        return JedisBasedProxyManager.builderFor(jedisPool).build();
    }
}
