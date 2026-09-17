package com.URI.URL_Shortner.Ratelimit;

import com.URI.URL_Shortner.Ratelimit.RateLimitTier;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RateLimitKeyResolver {

    private static final String API_KEY_HEADER = "X-API-Key";
    public ResolvedIdentity resolve(HttpServletRequest request) {
        String apiKey = request.getHeader(API_KEY_HEADER);
        if (apiKey != null && !apiKey.isBlank()) {
            return new ResolvedIdentity("rate-limit:apikey:" + apiKey, RateLimitTier.API_KEY);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            return new ResolvedIdentity("rate-limit:user:" + auth.getName(), RateLimitTier.AUTHENTICATED_USER);
        }

        String ip = extractClientIp(request);
        return new ResolvedIdentity("rate-limit:ip:" + ip, RateLimitTier.ANONYMOUS);
    }

    private String extractClientIp(HttpServletRequest request) {
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isBlank()) {
            return forwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }

    public record ResolvedIdentity(String key, RateLimitTier tier) {}
}