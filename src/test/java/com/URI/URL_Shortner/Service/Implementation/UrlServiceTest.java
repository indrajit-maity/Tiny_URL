package com.URI.URL_Shortner.Service.Implementation;


import com.URI.URL_Shortner.Configuration.UrlShortenerConfig;
import com.URI.URL_Shortner.Dto.UrlResponseDto;
import com.URI.URL_Shortner.Dto.UserRequesDto;
import com.URI.URL_Shortner.Dto.UserResponseDto;
import com.URI.URL_Shortner.Entity.Url;
import com.URI.URL_Shortner.Entity.User;
import com.URI.URL_Shortner.Repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
@ExtendWith(MockitoExtension.class)
class UrlServiceTest {

    @Mock
    private UrlRepository urlRepository;

    @Mock
    private ModelMapper modelMapper;

    @Spy
    @InjectMocks
    private UrlServiceimpl urlService;

    @Test
    void createShortUrl_validRequest_returnsCorrectShortUrl() {
        UserRequesDto requestDto = new UserRequesDto();
        requestDto.setOriginalUrl(
                "https://amazon.in/product/xyz"
        );
        LocalDate expiryDate =
                LocalDate.now().plusDays(7);
        requestDto.setExpiryDate(expiryDate);
        User currentUser = new User();
        currentUser.setId(1L);
        currentUser.setUsername("Indrajit Maity");
        Url url = new Url();
        ReflectionTestUtils.setField(
                url,
                "id",
                1L
        );
        url.setOriginalUrl(
                "https://amazon.in/product/xyz"
        );
        url.setExpiryDate(expiryDate);
        url.setClickCount(0);
        url.setActive(true);
        url.setUser(currentUser);
        when(urlRepository.existsByOriginalUrlAndUser(anyString(), any(User.class)))
                .thenReturn(false);
        when(urlRepository.save(any(Url.class)))
                .thenReturn(url);
        doReturn("15FTGh")
                .when(urlService)
                .AutogenerateShortCode(any(Url.class));
        UserResponseDto expectedResponse =
                UserResponseDto.builder()
                        .shortUrl(
                                "http://localhost:8081/api/15FTGh"
                        )
                        .expiryDate(expiryDate)
                        .isActive(true)
                        .build();
        when(modelMapper.map(
                any(Url.class),
                eq(UserResponseDto.class)
        )).thenReturn(expectedResponse);
        UserResponseDto responseDto =
                urlService.createShortUrl(
                        requestDto,
                        currentUser
                );
        assertEquals(
                "http://localhost:8081/api/15FTGh",
                responseDto.getShortUrl()
        );
        assertEquals(
                expiryDate,
                responseDto.getExpiryDate()
        );
        assertEquals(
                0,
                responseDto.getCount()
        );
        assertTrue(
                responseDto.isActive()
        );
        verify(urlRepository, times(2))
                .save(any(Url.class));
    }
}