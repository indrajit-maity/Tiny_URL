package com.URI.URL_Shortner.Ratelimit;



import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RateLimitFilter extends OncePerRequestFilter {

    private final RateLimiterService rateLimiterService;
    private final RateLimitKeyResolver keyResolver;
    private final ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{
        RateLimitKeyResolver.ResolvedIdentity identity=keyResolver.resolve(request);
        Bucket bucket= rateLimiterService.resolveBucket(identity.key(),identity.tier());
        ConsumptionProbe probe=bucket.tryConsumeAndReturnRemaining(1);
        response.setHeader("X-RateLimit-Remaining",String.valueOf(probe.getRemainingTokens()));
        if(probe.isConsumed()){
            filterChain.doFilter(request,response);
        }
        else{
            long waitSeconds= probe.getNanosToWaitForRefill()/1_000_000_000;
            response.setStatus((429));
            response.setHeader("Retry-After",String.valueOf(waitSeconds));
            response.setContentType(("application/json"));
            response.getWriter().write(objectMapper.writeValueAsString(
                    Map.of(
                            "message", "Rate limit exceeded. Try again later.",
                            "retryAfterSeconds", waitSeconds
                    )
            ));
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return request.getRequestURI().matches("^/[a-zA-Z0-9-]{1,20}$")
                && !request.getRequestURI().startsWith("/api");

    }

}
