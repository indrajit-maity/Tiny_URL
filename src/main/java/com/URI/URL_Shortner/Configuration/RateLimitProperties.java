package com.URI.URL_Shortner.Configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
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

    public long getAnonymousCapacity() {
        return anonymousCapacity;
    }
    public void setAnonymousCapacity(long v) {
        this.anonymousCapacity = v;
    }
    public long getAnonymousRefillTokens() {
        return anonymousRefillTokens;
    }
    public void setAnonymousRefillTokens(long v) {
        this.anonymousRefillTokens = v;
    }
    public long getAnonymousRefillMinutes() {
        return anonymousRefillMinutes;
    }
    public void setAnonymousRefillMinutes(long v) {
        this.anonymousRefillMinutes = v;
    }

    public long getUserCapacity() {
        return userCapacity;
    }
    public void setUserCapacity(long v) {
        this.userCapacity = v;
    }
    public long getUserRefillTokens() {
        return userRefillTokens;
    }
    public void setUserRefillTokens(long v) {
        this.userRefillTokens = v;
    }
    public long getUserRefillMinutes() {
        return userRefillMinutes;
    }
    public void setUserRefillMinutes(long v) {
        this.userRefillMinutes = v;
    }

    public long getApiKeyCapacity() {
        return apiKeyCapacity;
    }
    public void setApiKeyCapacity(long v) {
        this.apiKeyCapacity = v;
    }
    public long getApiKeyRefillTokens() {
        return apiKeyRefillTokens;
    }
    public void setApiKeyRefillTokens(long v) {
        this.apiKeyRefillTokens = v;
    }
    public long getApiKeyRefillMinutes() {
        return apiKeyRefillMinutes;
    }
    public void setApiKeyRefillMinutes(long v) {
        this.apiKeyRefillMinutes = v;
    }

}
