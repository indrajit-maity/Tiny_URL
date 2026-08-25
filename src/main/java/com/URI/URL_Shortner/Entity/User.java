package com.URI.URL_Shortner.Entity;


import com.URI.URL_Shortner.Entity.Type.AuthproviderType;
import com.URI.URL_Shortner.Entity.Type.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users",indexes = @Index(name = "idx_provider_id_provider_type",columnList = "provider,auth_provider_type"))
public class User implements UserDetails {

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

    @Column(nullable = false,unique = true)
    private String password;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "auth_provider_type")
    @Enumerated(EnumType.STRING)
    private AuthproviderType authproviderType;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    Set<RoleType> roles=new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities=new HashSet<>();
        roles.forEach(
                role->{
                    Set<SimpleGrantedAuthority> permissions=new HashSet<>();
                }
        );
        return authorities;
    }

}
