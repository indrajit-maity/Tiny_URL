package com.URI.URL_Shortner.Configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "app.rate-limit")
public class RateLimitProperties {
    private long anonymousCapacity = 10;
    private long anonymousRefillTokens = 10;
    private long anonymousRefillMinutes = 1;

    private long userCapacity = 60;
    private long userRefillTokens = 60;
    private long userRefillMinutes = 1;

    private long apiKeyCapacity = 300;
    private long apiKeyRefillTokens = 300;
    private long apiKeyRefillMinutes = 1;
}
