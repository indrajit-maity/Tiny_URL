package com.URI.URL_Shortner.Repository;

import com.URI.URL_Shortner.Entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    boolean existsByShortCode(String shortCode);
}
