package com.neueda.shortener.dao;

import com.neueda.shortener.model.Link;
import com.neueda.shortener.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatsRepository extends JpaRepository<Stats, Long> {

    Stats findByLink(Link link);
}