package com.neueda.shortener;

import com.neueda.shortener.dao.LinkRepository;
import com.neueda.shortener.model.Link;
import com.neueda.shortener.service.LinkService;
import com.neueda.shortener.util.LinkUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * Spring boot inizializer. Creates initial data.
 *
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
@Component
class Initializer implements CommandLineRunner {

    private final LinkService linkService;
    private final LinkRepository linkRepository;

    public Initializer(LinkService linkService, LinkRepository linkRepository) {
        this.linkService = linkService;
        this.linkRepository = linkRepository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("https://www.google.com", "https://www.neueda.com/about-us").forEach(url ->
                linkService.save(new Link(url, LinkUtils.shortenize(url)))
        );
    }
}