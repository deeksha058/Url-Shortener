package com.UrlShortener.UrlShortener.Controller;

import com.UrlShortener.UrlShortener.Entity.UrlMapping;
import com.UrlShortener.UrlShortener.Service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlShortenerController {

    private final UrlShortenerService service;

    @Autowired
    public UrlShortenerController(UrlShortenerService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl) {
        String shortenedUrl = service.shortenUrl(originalUrl);
        return new ResponseEntity<>(shortenedUrl, HttpStatus.OK);
    }

    @GetMapping("/{shortenedUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortenedUrl) {
        String originalUrl = service.getOriginalUrl(shortenedUrl);
        if (originalUrl == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, originalUrl)
                .build();
    }

}
