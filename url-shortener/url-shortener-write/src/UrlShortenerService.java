package com.example.urlshortenerwrite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class UrlShortenerService {

    @Autowired
    private UrlMappingRepository repository;

    public String shortenUrl(String originalUrl, long expirationDays) {
        String shortUrl = Base64.getEncoder().encodeToString(originalUrl.getBytes()).substring(0, 7);
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(expirationDays);

        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setExpirationDate(expirationDate);

        repository.save(urlMapping);
        return shortUrl;
    }
}