package com.neueda.shortener.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllLinks() throws Exception {
        String expectedResult = "[{\"id\":1,\"url\":\"https://www.google.com\",\"shortUrl\":\"http://short.me/331e5b6b\"},{\"id\":2,\"url\":\"https://www.neueda.com/about-us\",\"shortUrl\":\"http://short.me/e77e23b8\"}]";
        this.mockMvc.perform(get("/api/links")).andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    public void shouldReturn1Link() throws Exception {
        String expectedResult = "{\"id\":1,\"url\":\"https://www.google.com\",\"shortUrl\":\"http://short.me/331e5b6b\"}";

        this.mockMvc.perform(get("/api/link/1")).andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    public void shouldCreateAndDeleteLink() throws Exception {
        String createJSON = "{\"url\":\"https://www.docker.com/products/docker-desktop\"}";
        String expectedResultCreateJSON ="{\"id\":3,\"url\":\"https://www.docker.com/products/docker-desktop\",\"shortUrl\":\"http://short.me/ec0ed78d\"}";
        this.mockMvc.perform(post("/api/link").contentType(MediaType.APPLICATION_JSON).content(createJSON)).andExpect(status().isCreated())
                .andExpect(content().json(expectedResultCreateJSON));

        this.mockMvc.perform(delete("/api/link/3")).andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateLink() throws Exception {
        String updateJSON = "{\"id\":1,\"url\":\"https://www.docker.com/products/docker-desktop\"}";
        String expectedResultUpdateJSON ="{\"id\":1,\"url\":\"https://www.docker.com/products/docker-desktop\",\"shortUrl\":\"http://short.me/ec0ed78d\"}";
        String revertUpdateJSON = "{\"id\":1,\"url\":\"https://www.google.com\"}";
        String expectedResultRevertUpdateJSON ="{\"id\":1,\"url\":\"https://www.google.com\",\"shortUrl\":\"http://short.me/331e5b6b\"}";

        this.mockMvc.perform(put("/api/link").contentType(MediaType.APPLICATION_JSON).content(updateJSON)).andExpect(status().isOk())
                .andExpect(content().json(expectedResultUpdateJSON));

        this.mockMvc.perform(put("/api/link").contentType(MediaType.APPLICATION_JSON).content(revertUpdateJSON)).andExpect(status().isOk())
                .andExpect(content().json(expectedResultRevertUpdateJSON));

    }

    
}