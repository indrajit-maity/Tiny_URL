package com.URI.URL_Shortner.Service.Implementation;


import com.URI.URL_Shortner.Dto.UserRequesDto;
import com.URI.URL_Shortner.Dto.UserResponseDto;
import com.URI.URL_Shortner.Entity.Url;
import com.URI.URL_Shortner.Exception.DomainNotAllowedException;
import com.URI.URL_Shortner.Repository.UrlRepository;
import com.URI.URL_Shortner.Service.UrlSevice;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;

import static org.yaml.snakeyaml.events.Event.ID.Alias;

@Service
@RequiredArgsConstructor
public class UrlServiceimpl implements UrlSevice {

    private  final ModelMapper modelMapper;
    private final UrlRepository urlRepository;

    @Override
    public UserResponseDto createShortUrl(UserRequesDto userRequesDto) {
        String domain=userRequesDto.getOriginalUrl();
        validNotSelfReference(domain);
        ValidAndReserveAlias(userRequesDto.getShortCode());
      Url url=urlRepository.save(Url.builder()
                        .originalUrl(userRequesDto.getOriginalUrl())
                        .createdAt(LocalDate.now())
                        .shortCode(userRequesDto.getShortCode())
                        .expiryDate(userRequesDto.getExpiryDate())
                        .shortUrl(userRequesDto.getShortUrl())
                        .clickCount(5)
                        .isActive(true)
                        .build()
        );
        return (modelMapper.map(url,UserResponseDto.class));
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

}
