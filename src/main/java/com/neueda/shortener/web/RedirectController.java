package com.neueda.shortener.web;

import com.neueda.shortener.model.Link;
import com.neueda.shortener.service.LinkService;
import com.neueda.shortener.service.StatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
@RestController
@RequestMapping("/s")
class RedirectController {

    private final Logger log = LoggerFactory.getLogger(RedirectController.class);
    private LinkService linkService;
    private StatsService statsService;

    public RedirectController(LinkService linkService, StatsService statsService) {
        this.linkService = linkService;
        this.statsService = statsService;
    }

    @GetMapping("/{slug}")
    ResponseEntity<?> redirectLink(@PathVariable String slug) {
        Optional<Link> linkOptional = linkService.findFirstBySlug(slug);
        return linkOptional.map(link -> {
            statsService.incrementRedirectCount(link);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", link.getUrl());
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
        })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
