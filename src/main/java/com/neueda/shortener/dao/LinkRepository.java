package com.neueda.shortener.dao;

import com.neueda.shortener.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Link findByUrl(String url);

    Optional<Link> findFirstBySlug(String url);
}