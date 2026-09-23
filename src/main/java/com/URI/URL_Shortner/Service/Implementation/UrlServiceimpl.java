package com.URI.URL_Shortner.Service.Implementation;


import com.URI.URL_Shortner.Configuration.UrlShortenerConfig;
import com.URI.URL_Shortner.Dto.UrlResponseDto;
import com.URI.URL_Shortner.Dto.UserRequesDto;
import com.URI.URL_Shortner.Dto.UserResponseDto;
import com.URI.URL_Shortner.Entity.Url;
import com.URI.URL_Shortner.Entity.User;
import com.URI.URL_Shortner.Exception.DomainNotAllowedException;
import com.URI.URL_Shortner.Exception.ShortCodeGenerationException;
import com.URI.URL_Shortner.Repository.UrlRepository;
import com.URI.URL_Shortner.Service.UrlService;
import com.URI.URL_Shortner.Service.Util.Base62encode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlServiceimpl implements UrlService {

    private static final Logger logger = LoggerFactory.getLogger(UrlServiceimpl.class);
    private  final ModelMapper modelMapper;
    private final UrlRepository urlRepository;
    private final UrlShortenerConfig urlShortenerConfig;

    @Value(("${app.domain}"))
    private String ownDomain;

    @Override
    @Transactional
    public UserResponseDto createShortUrl(UserRequesDto userRequesDto, User currentuser) {
        String name=currentuser.getUsername();
        String longUrl=userRequesDto.getOriginalUrl();
        validNotSelfReference(longUrl);
        Url Entity=Url.builder()
                .originalUrl(longUrl)
                .createdAt(LocalDate.now())
                .expiryDate(userRequesDto.getExpiryDate())
                .user(currentuser)
                .build();
        Url savedEntity;
        try{
            savedEntity=urlRepository.save(Entity);
        } catch (Exception e) {
            logger.error("Failed to Persist URL entry for longUrl={}",longUrl,e);
            throw new ShortCodeGenerationException("Failed to Saved url",e);
        }
        String shortCode=AutogenerateShortCode(savedEntity);
        savedEntity.setShortCode(shortCode);
        logger.info("Created short url for longUrl={}",shortCode,longUrl);
        String fullShortUrl=ownDomain+"/"+shortCode;
        savedEntity.setShortUrl(fullShortUrl);
        savedEntity.setClickCount(0);
        savedEntity.setActive(true);
        savedEntity=urlRepository.save(savedEntity);
        System.out.println(fullShortUrl);
        System.out.println(savedEntity.getOriginalUrl());
        return modelMapper.map(savedEntity,UserResponseDto.class);

    }

    private void validNotSelfReference(String domain){
        String ownDomain="localhost";
        URI uri=URI.create(domain);
        String host= uri.getHost();
        if(host!=null && host.equals(ownDomain)){
            throw new DomainNotAllowedException("Cannot create URL:which is already shortner");
        }
    }

    private void ValidAndReserveAlias(String shortcode){
        if(shortcode==null || shortcode.isEmpty()){
            throw new DomainNotAllowedException("Cannot create URL:shortcode is empty");
        }
        if(urlRepository.existsByShortCode(shortcode)){
            throw new DomainNotAllowedException("Alias "+shortcode+" already in use");
        }
    }


    private String AutogenerateShortCode(Url url){
        Url saveUrl=url;
        long CounterValue= saveUrl.getId();
        long finalNumber=CounterValue+urlShortenerConfig.Id_Offset;
        String shortCode= Base62encode.encode(finalNumber);
        saveUrl.setShortCode(shortCode);
        urlRepository.save(saveUrl);
        return shortCode;
    }


    @Override
   @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<UrlResponseDto> getUrlsForUser(User currentUser, PageRequest pageRequest) {
        Page<Url> urlPage=urlRepository.findByUserId(currentUser.getId(),pageRequest);
        return urlPage.map(url -> modelMapper.map(url,UrlResponseDto.class));
    }


}
