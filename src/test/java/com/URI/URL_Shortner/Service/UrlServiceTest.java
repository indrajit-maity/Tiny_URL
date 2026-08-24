package com.URI.URL_Shortner.Service;


import com.URI.URL_Shortner.Dto.UserRequesDto;
import com.URI.URL_Shortner.Service.Implementation.UrlServiceimpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class UrlServiceTest {


    @Autowired
    private UrlServiceimpl urlServiceimpl;

    @Test
    public void testUrlServiceTest(){
        UserRequesDto userRequesDto=new UserRequesDto("https://chatgpt.com/c/6a649907-fa30-83ee-aa8f-c795b67858ef",null, LocalDate.parse("2026-12-31"));
//        String code=urlServiceimpl.AutogenerateShortCode(userRequesDto);
//        System.out.println(code);
    }

}
