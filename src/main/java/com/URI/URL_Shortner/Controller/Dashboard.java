package com.URI.URL_Shortner.Controller;

import com.URI.URL_Shortner.Dto.UrlResponseDto;
import com.URI.URL_Shortner.Entity.User;
import com.URI.URL_Shortner.Service.UrlService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Endpoints for Dashboard")
public class Dashboard {

    public final UrlService urlService;
    @GetMapping("/myurls")
    public ResponseEntity<Page<UrlResponseDto>> getMyUrls(@AuthenticationPrincipal User currentUser,
                                                          @RequestParam(defaultValue = "0")int page,
                                                          @RequestParam(defaultValue = "20")int size){
        Page<UrlResponseDto> myurls= urlService.getUrlsForUser(currentUser, PageRequest.of(page,size));

        return ResponseEntity.ok(myurls);
    }
}
