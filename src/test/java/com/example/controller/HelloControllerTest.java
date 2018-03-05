package com.example.controller;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testShow() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello").accept(MediaType.TEXT_HTML);
        ResultActions resultActions = this.mvc.perform(requestBuilder);
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateSession() throws Exception {
        String attributeName = "myParam";
        String attributeValue = UUID.randomUUID().toString();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/hello").accept(MediaType.TEXT_HTML)
                .param("name", attributeName).param("value", attributeValue);
        this.mvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.request().sessionAttribute(attributeName, attributeValue));
    }
}