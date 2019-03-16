package com.neueda.shortener.web;

import com.neueda.shortener.model.Stats;
import com.neueda.shortener.service.StatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
@RestController
@RequestMapping("/api")
class StatsController {
    private StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/stats")
    Collection<Stats> stats() {
        return statsService.findAll();
    }

    @GetMapping("/stats/{id}")
    ResponseEntity<?> getStatsByLinkId(@PathVariable Long id) {
        return statsService.findById(id).map(stats -> ResponseEntity.ok().body(stats))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
