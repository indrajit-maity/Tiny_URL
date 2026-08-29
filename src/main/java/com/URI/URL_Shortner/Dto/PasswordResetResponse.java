package com.URI.URL_Shortner.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordResetResponse {
    private String message;
    private boolean success;
    private  String email;
    private LocalDateTime timestamp;
    private String resetToken;
}
