package com.URI.URL_Shortner.Service;

import com.URI.URL_Shortner.Dto.UrlResponseDto;
import com.URI.URL_Shortner.Dto.UserRequesDto;
import com.URI.URL_Shortner.Dto.UserResponseDto;
import com.URI.URL_Shortner.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface UrlService {

     Page<UrlResponseDto> getUrlsForUser(User currentUser, PageRequest pageRequest);

    UserResponseDto createShortUrl(UserRequesDto userRequesDto, User currentuser);
}
