package com.URI.URL_Shortner.Entity;


import jakarta.persistence.*;
import lombok.*;

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
    private long id;
    @Column(name = "user_name", nullable = false, unique = true)
    private String username;
    @Column(name = "email", nullable = false,unique = true)
    private String email;
    @Column(name = "mobile_no", nullable = false, unique = true)
    private String phoneNumber;
}
