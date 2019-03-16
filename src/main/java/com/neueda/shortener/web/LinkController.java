package com.neueda.shortener.web;

import com.neueda.shortener.model.Link;
import com.neueda.shortener.service.LinkService;
import com.neueda.shortener.util.LinkUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

/**
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
@RestController
@RequestMapping("/api")
class LinkController {

    private final Logger log = LoggerFactory.getLogger(LinkController.class);
    private LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/links")
    Collection<Link> Links() {
        return linkService.findAll();
    }

    @GetMapping("/link/{id}")
    ResponseEntity<?> getLink(@PathVariable Long id) {
        Optional<Link> link = linkService.findById(id);
        return link.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/link")
    ResponseEntity<Link> createLink(@Valid @RequestBody Link link) throws URISyntaxException {
        log.info("Request to save Link: {}", link);
        link.setSlug(LinkUtils.shortenize(link.getUrl()));
        Link result = linkService.save(link);
        return ResponseEntity.created(new URI("/api/link/" + result.getId()))
                .body(result);
    }

    @PutMapping("/link")
    ResponseEntity<Link> updateLink(@Valid @RequestBody Link link) {
        log.info("Request to update Link: {}", link);
        link.setSlug(LinkUtils.shortenize(link.getUrl()));
        Link result = linkService.save(link);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/link/{id}")
    public ResponseEntity<?> deleteLink(@PathVariable Long id) {
        log.info("Request to delete Link: {}", id);
        linkService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
