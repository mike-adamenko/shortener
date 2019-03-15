package com.neueda.shortener;

import com.neueda.shortener.model.Link;
import com.neueda.shortener.model.LinkRepository;
import com.neueda.shortener.util.LinkUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final LinkRepository repository;

    public Initializer(LinkRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("http://google.com", "https://github.com/maxaugustyn/Shortenize/tree/master/public").forEach(url ->
                repository.save(new Link(url, LinkUtils.shortenize(url)))
        );

        repository.findAll().forEach(System.out::println);
    }
}