package com.neueda.shortener.web;

import com.neueda.shortener.model.Link;
import com.neueda.shortener.model.LinkRepository;
import com.neueda.shortener.util.LinkUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class LinkController {

    private final Logger log = LoggerFactory.getLogger(LinkController.class);
    private LinkRepository linkRepository;

    public LinkController(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @GetMapping("/links")
    Collection<Link> Links() {
        return linkRepository.findAll();
    }

    @GetMapping("/link/{id}")
    ResponseEntity<?> getLink(@PathVariable Long id) {
        Optional<Link> link = linkRepository.findById(id);
        return link.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/forward")
    @CrossOrigin
    ResponseEntity<?> forwardLink(String shortUrl) {
        Optional<Link> link = linkRepository.findByShortUrl(shortUrl);
        return link.map(response -> {
            HttpHeaders headers = new HttpHeaders();
//            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Location", response.getUrl());
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
        })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/link")
    ResponseEntity<Link> createLink(@Valid @RequestBody Link link) throws URISyntaxException {
        log.info("Request to create Link: {}", link);
        link.setShortUrl(LinkUtils.shortenize(link.getUrl()));
        Link result = linkRepository.save(link);
        return ResponseEntity.created(new URI("/api/link/" + result.getId()))
                .body(result);
    }

    @PutMapping("/link")
    ResponseEntity<Link> updateLink(@Valid @RequestBody Link link) {
        log.info("Request to update Link: {}", link);
        link.setShortUrl(LinkUtils.shortenize(link.getUrl()));
        Link result = linkRepository.save(link);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/link/{id}")
    public ResponseEntity<?> deleteLink(@PathVariable Long id) {
        log.info("Request to delete Link: {}", id);
        linkRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
