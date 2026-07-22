package com.URI.URL_Shortner.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "Email", nullable = false,unique = true)
    private String email;
    @Column(name = "MobileNo", nullable = false, unique = true)
    private String phoneNumber;
}
