package com.example.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class SanitizingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testEmptyRequest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sanitizing").accept(MediaType.TEXT_HTML);
        MvcResult result = mvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertEquals("sanitizing", result.getModelAndView().getViewName());
    }

    @Test
    public void testVerifyRequestSafe() throws Exception {
        String parameterName = "myParam";
        String parameterValue = "myValue";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sanitizing").accept(MediaType.TEXT_HTML)
                .param(parameterName, parameterValue);
        MvcResult result = mvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertEquals("sanitizing", result.getModelAndView().getViewName());
    }

    @Test
    public void testVerifyRequestNotSafeParameterValue() throws Exception {
        String parameterName = "myParam";
        String parameterValue = "<a href='http://example.com'>myValue</a>";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sanitizing").accept(MediaType.TEXT_HTML)
                .param(parameterName, parameterValue);
        MvcResult result = mvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertEquals("notSafe", result.getModelAndView().getViewName());
    }
}