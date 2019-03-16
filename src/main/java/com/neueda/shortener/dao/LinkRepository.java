package com.neueda.shortener.dao;

import com.neueda.shortener.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findFirstBySlug(String url);
}