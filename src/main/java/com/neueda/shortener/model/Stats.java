package com.neueda.shortener.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Stats {
    @Id
    private long id;

    private long redirectCount;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    private Link link;

    public Stats() {
    }

    public Stats(Link link) {
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRedirectCount() {
        return redirectCount;
    }

    public void setRedirectCount(Long redirectCount) {
        this.redirectCount = redirectCount;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "id=" + id +
                ", redirectCount=" + redirectCount +
                '}';
    }
}
