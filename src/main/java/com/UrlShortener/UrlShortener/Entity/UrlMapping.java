package com.UrlShortener.UrlShortener.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UrlMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String shortenedUrl;

    private String originalUrl;
}
