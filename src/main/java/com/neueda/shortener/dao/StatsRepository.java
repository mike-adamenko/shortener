package com.neueda.shortener.dao;

import com.neueda.shortener.model.Link;
import com.neueda.shortener.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Statistics DAO
 *
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
public interface StatsRepository extends JpaRepository<Stats, Long> {
    Stats findByLink(Link link);
}