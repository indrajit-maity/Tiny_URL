package com.URI.URL_Shortner.Dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponse {
    String JWT_TOKEN;
    Long userId;
}
