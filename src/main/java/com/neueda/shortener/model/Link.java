package com.neueda.shortener.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Link {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String url;
    private String slug;
    @OneToOne(mappedBy = "link", cascade = CascadeType.ALL)
    private Stats stats;

    public Link() {
    }

    public Link(String url, String slug) {
        this.url = url;
        this.slug = slug;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", slug='" + slug + '\'' +
                ", stats=" + stats +
                '}';
    }
}
