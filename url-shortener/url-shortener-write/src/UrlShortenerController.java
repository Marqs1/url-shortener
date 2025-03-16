package com.example.urlshortenerwrite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam String originalUrl, @RequestParam long expirationDays) {
        return urlShortenerService.shortenUrl(originalUrl, expirationDays);
    }
}