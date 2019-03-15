package com.neueda.shortener.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RobotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnValidResult() throws Exception {
        String content = "POSITION 1 3 EAST //sets the initial position for the robot as x, y.\n" +
                "FORWARD 3 //lets the robot do 3 steps forward\n" +
                "WAIT //lets the robot do nothing\n" +
                "TURNAROUND //lets the robot turn around\n" +
                "FORWARD 1 //lets the robot do 1 step forward\n" +
                "RIGHT //lets the robot turn right\n" +
                "FORWARD 2 //lets the robot do 2 steps forward";
        this.mockMvc.perform(post("/api/calculateNewPosition").contentType(MediaType.TEXT_PLAIN).content(content)).andExpect(status().isOk())
                .andExpect(content().json("{'x':3,'y':1,'direction':'NORTH','onTable':true,'currentStatus':'3,1,NORTH'}"));
    }

    @Test
    public void shouldReturnValidResultForRequestWithoutComments() throws Exception {
        String content = "POSITION 1 3 EAST\n" +
                "FORWARD 3\n" +
                "WAIT\n" +
                "TURNAROUND\n" +
                "FORWARD 1\n" +
                "RIGHT\n" +
                "FORWARD 2";
        this.mockMvc.perform(post("/api/calculateNewPosition").contentType(MediaType.TEXT_PLAIN).content(content)).andExpect(status().isOk())
                .andExpect(content().json("{'x':3,'y':1,'direction':'NORTH','onTable':true,'currentStatus':'3,1,NORTH'}"));
    }

    @Test
    public void shouldReturnValidResultForOutOfBoard() throws Exception {
        String content = "POSITION 1 30 EAST";
        this.mockMvc.perform(post("/api/calculateNewPosition").contentType(MediaType.TEXT_PLAIN).content(content)).andExpect(status().isOk())
                .andExpect(content().json("{'x':null,'y':null,'direction':null,'onTable':false,'currentStatus':'IGNORED'}"));
    }

    @Test
    public void shouldReturnValidResultForInvalidRequest() throws Exception {
        String content = "invalid";
        this.mockMvc.perform(post("/api/calculateNewPosition").contentType(MediaType.TEXT_PLAIN).content(content)).andExpect(status().isOk())
                .andExpect(content().json("{'x':null,'y':null,'direction':null,'onTable':false,'currentStatus':'IGNORED'}"));
    }

    @Test
    public void shouldIgnoreInvalidCommand() throws Exception {
        String content = "POSITION 1 3 EAST\n" +
                "FORWdfgARD 3\n";
        this.mockMvc.perform(post("/api/calculateNewPosition").contentType(MediaType.TEXT_PLAIN).content(content)).andExpect(status().isOk())
                .andExpect(content().json("{'x':1,'y':3,'direction':'EAST','onTable':true,'currentStatus':'1,3,EAST'}"));
    }

    @Test
    public void shouldIgnoreForward5Steps() throws Exception {
        String content = "POSITION 0 0 SOUTH //sets the initial position for the robot as x, y.\n" +
                "FORWARD 5 //lets the robot do 5 steps forward";
        this.mockMvc.perform(post("/api/calculateNewPosition").contentType(MediaType.TEXT_PLAIN).content(content)).andExpect(status().isOk())
                .andExpect(content().json("{'x':0,'y':0,'direction':'SOUTH','onTable':true,'currentStatus':'0,0,SOUTH'}"));
    }

    @Test
    public void shouldReturn400() throws Exception {
        String content = "";
        this.mockMvc.perform(post("/api/calculateNewPosition").contentType(MediaType.TEXT_PLAIN).content(content)).andExpect(status().is4xxClientError());
    }
}