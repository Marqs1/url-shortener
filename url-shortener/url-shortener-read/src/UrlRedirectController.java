package com.example.urlshortenerread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class UrlRedirectController {

    @Autowired
    private UrlMappingRepository repository;

    @GetMapping("/{shortUrl}")
    public void redirectToOriginalUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        UrlMapping urlMapping = repository.findByShortUrl(shortUrl);
        if (urlMapping != null && urlMapping.getExpirationDate().isAfter(LocalDateTime.now())) {
            response.sendRedirect(urlMapping.getOriginalUrl());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}