package com.URI.URL_Shortner.Dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupResponse {
    private Long UserId;
    private String UserName;
    private String UserEmail;
    private String UserPassword;
}
