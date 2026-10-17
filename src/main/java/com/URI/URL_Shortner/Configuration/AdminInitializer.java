package com.URI.URL_Shortner.Configuration;

import com.URI.URL_Shortner.Entity.Type.AuthproviderType;
import com.URI.URL_Shortner.Entity.Type.RoleType;
import com.URI.URL_Shortner.Entity.User;
import com.URI.URL_Shortner.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;

@Configuration
public class AdminInitializer {

    @Bean
    CommandLineRunner createAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            String email="indra@gmail.com";
            String password="123456";
//            String email = System.getenv("ADMIN_EMAIL");
//            String password = System.getenv("ADMIN_PASSWORD");

            if (email == null || email.isBlank()
                    || password == null || password.isBlank()) {
                throw new IllegalStateException(
                        "Set ADMIN_EMAIL and ADMIN_PASSWORD environment variables"
                );
            }

            if (userRepository.findByEmail(email).isPresent()) {
                System.out.println("Admin account already exists.");
                return;
            }

            User admin = User.builder()
                    .username("Administrator")
                    .email(email)
                    .phoneNumber("7876398237")
                    .password(passwordEncoder.encode(password))
                    .providerId(null)
                    .authproviderType(AuthproviderType.EMAIL)
                    .roles(Set.of(RoleType.ADMIN))
                    .createdDate(LocalDateTime.now())
                    .build();

            userRepository.save(admin);

            System.out.println("Default admin account created.");
        };
    }
}
