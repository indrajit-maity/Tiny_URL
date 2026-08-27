package com.URI.URL_Shortner.Dto;


import com.URI.URL_Shortner.Entity.Type.RoleType;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SignupRequest {
    private  String username;
    private  String password;
    private String email;
    private String phoneNumber;
    private Set<RoleType> roles=new HashSet<>();
}
