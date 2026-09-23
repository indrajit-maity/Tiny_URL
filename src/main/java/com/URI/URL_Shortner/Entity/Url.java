package com.URI.URL_Shortner.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "url",
indexes = {
        @Index(name = "idx_short_code",columnList = "short_code"),
        @Index(name="idx_original_url",columnList = "original_url"),
        @Index(name = "idx_user_id",columnList = "user_id")
}
)
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",unique = true)
    private  long id;

    @Column(name = "original_url", nullable = false, length = 255)
    private String originalUrl;

    @Column(name = "short_code", unique = true)
    private String shortCode;

    @Column(name = "short_url", unique = true,length = 255)
    private  String shortUrl;

    @Column(name = "click_count")
    @Builder.Default
    private int clickCount=0;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "is_active")
    @Builder.Default
    private  boolean isActive=true;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id",nullable = false)
   private User user;

    @Version
    @Column(name = "version")
    @Builder.Default
    private  Long version=0L; //optimistic Locking

}
