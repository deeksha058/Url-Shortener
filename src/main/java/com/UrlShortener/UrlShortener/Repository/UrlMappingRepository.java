package com.UrlShortener.UrlShortener.Repository;

import com.UrlShortener.UrlShortener.Entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    UrlMapping findByShortenedUrl(String shortenedUrl);
}
