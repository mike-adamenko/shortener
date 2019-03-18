package com.neueda.shortener.service;

import com.neueda.shortener.dao.StatsRepository;
import com.neueda.shortener.model.Link;
import com.neueda.shortener.model.Stats;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Service layer for Statistics. Handles buisness logic for @{@link Stats}.
 *
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
@Service
public class StatsService {

    private final StatsRepository statsRepository;

    StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public void incrementRedirectCount(Link link) {
        Stats stats = statsRepository.findByLink(link);
        stats.setRedirectCount(stats.getRedirectCount() + 1);
        statsRepository.save(stats);
    }

    public Collection<Stats> findAll() {
        return statsRepository.findAll();
    }

    public Optional<Stats> findById(Long id) {
        return statsRepository.findById(id);
    }
}
