package com.URI.URL_Shortner.Service;

import com.URI.URL_Shortner.Dto.UserRequesDto;
import com.URI.URL_Shortner.Dto.UserResponseDto;

public interface UrlSevice {

    UserResponseDto createShortUrl(UserRequesDto userRequesDto);
}
