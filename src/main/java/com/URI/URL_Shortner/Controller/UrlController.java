package com.URI.URL_Shortner.Controller;


import com.URI.URL_Shortner.Dto.UserRequesDto;
import com.URI.URL_Shortner.Dto.UserResponseDto;
import com.URI.URL_Shortner.Service.UrlSevice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/urls")
@Tag(name = "URL Shortener", description = "Endpoints for URL shortening and management")
public class UrlController {

    private  final UrlSevice urlSevice;


    @Operation(summary = "Create Short URL", description = "Creates a short URL for the provided original URL")
    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> CreateShorturl(@Valid @RequestBody UserRequesDto userRequesDto){
        UserResponseDto responseDto=urlSevice.createShortUrl(userRequesDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

}
