package com.URI.URL_Shortner.Ratelimit;

import com.URI.URL_Shortner.Configuration.RateLimitProperties;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.redis.jedis.cas.JedisBasedProxyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class RateLimiterService {

    private  final JedisBasedProxyManager<byte[]> proxyManager;
    private final RateLimitProperties properties;

    public Bucket resolveBucket(String key,RateLimitTier tier){
        byte[] keyBytes=key.getBytes(StandardCharsets.UTF_8);
        Supplier<BucketConfiguration> configSupplier=()->buildConfig(tier);
        return proxyManager.builder().build(keyBytes,configSupplier);
    }

    private BucketConfiguration buildConfig(RateLimitTier tier){
        Bandwidth limit=switch(tier) {
            case ANONYMOUS -> Bandwidth.classic(
                    properties.getAnonymousCapacity(),
                    io.github.bucket4j.Refill.intervally(
                            properties.getAnonymousRefillTokens(),
                            Duration.ofMinutes(properties.getAnonymousRefillMinutes())
                    )
            );
            case AUTHENTICATED_USER -> Bandwidth.classic(
                    properties.getUserCapacity(),
                    io.github.bucket4j.Refill.intervally(
                            properties.getUserRefillTokens(),
                            Duration.ofMinutes(properties.getUserRefillMinutes())
                    )
            );
            case API_KEY -> Bandwidth.classic(
                    properties.getApiKeyCapacity(),
                    io.github.bucket4j.Refill.intervally(
                            properties.getApiKeyRefillTokens(),
                            Duration.ofMinutes(properties.getApiKeyRefillMinutes())
                    )
            );
             };
        return BucketConfiguration.builder().addLimit(limit).build();
    }
}
