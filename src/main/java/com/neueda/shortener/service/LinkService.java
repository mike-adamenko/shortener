package com.neueda.shortener.service;

import com.neueda.shortener.dao.LinkRepository;
import com.neueda.shortener.dao.StatsRepository;
import com.neueda.shortener.model.Link;
import com.neueda.shortener.model.Stats;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class LinkService {

    private final LinkRepository linkRepository;
    private final StatsRepository statsRepository;

    LinkService(LinkRepository linkRepository, StatsRepository statsRepository) {
        this.linkRepository = linkRepository;
        this.statsRepository = statsRepository;
    }

    public Link save(Link link) {
        link.setStats(link.getId() == 0 ? new Stats(link) : statsRepository.findByLink(link));
        return linkRepository.save(link);
    }

    public Collection<Link> findAll() {
        return linkRepository.findAll();
    }

    public Optional<Link> findById(Long id) {
        return linkRepository.findById(id);
    }

    public void deleteById(Long id) {
        linkRepository.deleteById(id);
    }

    public Optional<Link> findFirstBySlug(String slug) {
        return linkRepository.findFirstBySlug(slug);
    }
}
