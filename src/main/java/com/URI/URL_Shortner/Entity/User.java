package com.URI.URL_Shortner.Entity;


import com.URI.URL_Shortner.Entity.Type.AuthproviderType;
import com.URI.URL_Shortner.Entity.Type.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name", nullable = false, unique = true)
    private String username;
    @Column(name = "email", nullable = false,unique = true)
    private String email;
    @Column(name = "mobile_no", nullable = false, unique = true)
    private String phoneNumber;
    @Column(name = "auth_provider_type")
    @Enumerated(EnumType.STRING)
    private AuthproviderType authproviderType;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    Set<RoleType> roles=new HashSet<>();


    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities=new HashSet<>();
        return authorities;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<SimpleGrantedAuthority> authorities=new HashSet<>();
//    //    roles.forEach(
//    //            role->{
//    //                Set<SimpleGrantedAuthority> permissions=new HashSet<>();
//    //            }
//    //    );
//        return authorities;
//    }

}
