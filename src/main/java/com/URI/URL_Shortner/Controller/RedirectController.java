package com.URI.URL_Shortner.Controller;

import com.URI.URL_Shortner.Service.Implementation.RedirectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/redirect")
@RequiredArgsConstructor
@Tag(name = "Redirect", description = "Endpoints for URL redirection")
public class RedirectController {

    private final RedirectService redirectService;

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode){
        String longUrl= redirectService.resolveLongUrl(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION,longUrl).build();
    }
}
