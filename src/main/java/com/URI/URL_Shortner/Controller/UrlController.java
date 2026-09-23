package com.URI.URL_Shortner.Controller;


import com.URI.URL_Shortner.Dto.UserRequesDto;
import com.URI.URL_Shortner.Dto.UserResponseDto;
import com.URI.URL_Shortner.Entity.User;
import com.URI.URL_Shortner.Service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/urls")
@Tag(name = "URL Shortener", description = "Endpoints for URL shortening and management")
public class UrlController {

    private  final UrlService urlSevice;


    @Operation(summary = "Create Short URL", description = "Creates a short URL for the provided original URL")
    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> CreateShorturl(@Valid @RequestBody UserRequesDto userRequesDto, @AuthenticationPrincipal User currentUser){
        UserResponseDto responseDto=urlSevice.createShortUrl(userRequesDto,currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

}
