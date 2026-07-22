package com.URI.URL_Shortner.Dto;


import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private  String shortUrl;
    private int Count;
    private LocalDate createdAt;
    private LocalDate expiryDate;
    private boolean isActive;
}
