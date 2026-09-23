package com.URI.URL_Shortner.Dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlResponseDto {

    private String shortUrl;
    private int clickCount;
    private LocalDate createdAt;
    private LocalDate expiryDate;
    private boolean active;
}