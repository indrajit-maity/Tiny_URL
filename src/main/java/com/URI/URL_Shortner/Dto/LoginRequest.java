package com.URI.URL_Shortner.Dto;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}
