package com.neueda.shortener.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Link findByUrl(String url);

    Optional<Link> findByShortUrl(String url);
}