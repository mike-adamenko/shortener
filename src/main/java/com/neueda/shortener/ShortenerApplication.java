package com.neueda.shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main driver app.
 *
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
@SpringBootApplication
public class ShortenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortenerApplication.class, args);
    }

}
