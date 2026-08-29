package com.URI.URL_Shortner.Dto;


import com.URI.URL_Shortner.Entity.Type.RoleType;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponse {
    String JWT_TOKEN;
    Long userId;
    String email;
    String username;
    Set<RoleType> roles;
}
