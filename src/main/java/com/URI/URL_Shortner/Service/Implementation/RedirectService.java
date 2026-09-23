package com.URI.URL_Shortner.Service.Implementation;

import com.URI.URL_Shortner.Entity.Url;
import com.URI.URL_Shortner.Exception.UrlNotFoundException;
import com.URI.URL_Shortner.Repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RedirectService {
    private static  final Logger logger= LoggerFactory.getLogger(RedirectService.class);
    private  final UrlRepository urlRepository;
    private final CacheService cacheService;

    @Transactional(readOnly = true)
    public String resolveLongUrl(String shortCode){
        String cachedLongUrl= cacheService.get(shortCode);
        if(cachedLongUrl!=null){
            logger.debug("Cache hit for shortCode={}",shortCode);
            return cachedLongUrl;
        }
        logger.debug("Cache miss for shortCode={}",shortCode);
        Url url=urlRepository.findByShortCode(shortCode).orElseThrow(()->new UrlNotFoundException("Short code not found: "+shortCode));
        cacheService.warmCache(shortCode,url.getOriginalUrl());
        return url.getOriginalUrl();
    }
}
