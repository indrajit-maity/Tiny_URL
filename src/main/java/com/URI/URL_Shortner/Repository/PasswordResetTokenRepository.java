package com.URI.URL_Shortner.Repository;

import com.URI.URL_Shortner.Entity.PasswordResetToken;
import com.URI.URL_Shortner.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
//    PasswordResetToken findByToken(String token);

//    long countByUserAndCreatedAfter(User user, LocalDateTime oneHourAgo);

//    void deleteByUser(User user);
}
