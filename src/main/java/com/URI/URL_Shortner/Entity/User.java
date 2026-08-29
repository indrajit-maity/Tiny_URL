package com.URI.URL_Shortner.Entity;


import com.URI.URL_Shortner.Entity.Type.AuthproviderType;
import com.URI.URL_Shortner.Entity.Type.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

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

    @Builder.Default
    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked=true;

    @Builder.Default
    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired=true;

    @Builder.Default
    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired=true;

    @Column(name = "failed_attempts", nullable = false)
    private int failedAttempts=0;

    @Column(name = "last_password_reset_date")
    private LocalDateTime lastPasswordResetDate;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "locked_at")
    private LocalDateTime lockedAt;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    Set<RoleType> roles=new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PasswordResetToken> resetTokens=new ArrayList<>();

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
