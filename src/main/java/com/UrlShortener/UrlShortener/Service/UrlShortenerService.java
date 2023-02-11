package com.UrlShortener.UrlShortener.Service;

import com.UrlShortener.UrlShortener.Entity.UrlMapping;
import com.UrlShortener.UrlShortener.Repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlShortenerService {

    private final UrlMappingRepository repository;

    @Autowired
    public UrlShortenerService(UrlMappingRepository repository) {
        this.repository = repository;
    }

    public String shortenUrl(String originalUrl) {
        String shortenedUrl = generateShortUrl();
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setShortenedUrl(shortenedUrl);
        repository.save(mapping);
        return shortenedUrl;
    }

    public String getOriginalUrl(String shortenedUrl) {
        UrlMapping mapping = repository.findByShortenedUrl(shortenedUrl);
        if (mapping == null) {
            return null;
        }
        return mapping.getOriginalUrl();
    }

    private String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int charactersLength = characters.length();
        Random random = new Random();
//        String tiny = "http:tinyurl.com/";
        for (int i = 0; i < 6; i++) {
            shortUrl.append(characters.charAt(random.nextInt(charactersLength)));
        }
        UrlMapping mapping = repository.findByShortenedUrl(shortUrl.toString());
        if (mapping != null) {
            return generateShortUrl();
        }
        String finalString = shortUrl.toString();
        return finalString;
    }

}
