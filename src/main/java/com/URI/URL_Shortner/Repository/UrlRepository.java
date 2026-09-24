package com.URI.URL_Shortner.Repository;

import com.URI.URL_Shortner.Entity.Url;
import com.URI.URL_Shortner.Entity.User;
import com.URI.URL_Shortner.Service.Implementation.CacheService;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortCode(String shortCode);
    boolean existsByShortCode(String shortCode);

    Page<Url> findByUserId(Long userId, PageRequest pageRequest);

//    boolean existsByOriginalUrl(String longUrl);

    boolean existsByOriginalUrlAndUser(String longUrl, User currentuser);

    Url findByOriginalUrlAndUser(String longUrl, User currentuser);
}
