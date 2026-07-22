package com.URI.URL_Shortner.Service.Implementation;


import com.URI.URL_Shortner.Dto.UserRequesDto;
import com.URI.URL_Shortner.Dto.UserResponseDto;
import com.URI.URL_Shortner.Entity.Url;
import com.URI.URL_Shortner.Repository.UrlRepository;
import com.URI.URL_Shortner.Service.UrlSevice;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UrlServiceimpl implements UrlSevice {

    private  final ModelMapper modelMapper;
    private final UrlRepository urlRepository;

    @Override
    public UserResponseDto createShortUrl(UserRequesDto userRequesDto) {
      Url url=urlRepository.save(Url.builder()
                        .originalUrl(userRequesDto.getOriginalUrl())
                        .createdAt(LocalDate.now())
                        .expiryDate(userRequesDto.getExpiryDate())
                        .shortUrl(userRequesDto.getShortUrl())
                        .clickCount(5)
                        .isActive(true)
                        .build()
        );
        return (modelMapper.map(url,UserResponseDto.class));
    }

}
