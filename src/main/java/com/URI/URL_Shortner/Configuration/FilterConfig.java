package com.URI.URL_Shortner.Configuration;


import com.URI.URL_Shortner.Ratelimit.RateLimitFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private  final RateLimitFilter rateLimitFilter;

    public FilterRegistrationBean<RateLimitFilter> rateLimitFilterFilterRegistrationBean(){
        FilterRegistrationBean<RateLimitFilter> registrationBean=new FilterRegistrationBean<>(rateLimitFilter);
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}
