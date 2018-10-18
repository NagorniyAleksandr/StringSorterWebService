package com.nagorniy.webservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StringsControllerTest {

    private static final String ENDPOINT_URL = "/sort/strings";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validRequestTest() throws Exception {
        String requestJson = "{\"strings\": [\n" +
                "\"Sound boy proceed to blast into the galaxy\",\n" +
                "\"Go back rocket man into the sky you'll see\",\n" +
                "\"Hear it all the time, come back rewind\",\n" +
                "\"Aliens are watching up in the sky\",\n" +
                "\"Sound boy process to blast into the galaxy\",\n" +
                "\"No one gonna harm you\",\n" +
                "\"They all want you to play I watch the birds of prey\"\n" +
                "]}";
        String expectedResult = "{\"result\": [\n" +
                "{\n" +
                "\"string\": \"Aliens are watching up in the sky\",\n" +
                "\"longestWord\": 8\n" +
                "},\n" +
                "{\n" +
                "\"string\": \"Sound boy process to blast into the galaxy\",\n" +
                "\"longestWord\": 7\n" +
                "},\n" +
                "{\n" +
                "\"string\": \"Sound boy proceed to blast into the galaxy\",\n" +
                "\"longestWord\": 7\n" +
                "},\n" +
                "{\n" +
                "\"string\": \"Go back rocket man into the sky you'll see\",\n" +
                "\"longestWord\": 6\n" +
                "},\n" +
                "{\n" +
                "\"string\": \"Hear it all the time, come back rewind\",\n" +
                "\"longestWord\": 6\n" +
                "}\n" +
                "]}";

        this.mockMvc.perform(post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(expectedResult));
    }

    @Test
    public void invalidRequestTest() throws Exception {
        String requestJson = "{}";

        this.mockMvc.perform(post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }
}