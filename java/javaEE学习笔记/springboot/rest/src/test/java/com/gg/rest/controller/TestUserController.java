package com.gg.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestUserController {
    private MockMvc mvc;
    RequestBuilder requestBuilder;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(
                new UserController()).build();
    }

    @Test
    public void testGet() throws Exception {
        requestBuilder = get("/user/");
        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void addUser() throws Exception {
        requestBuilder = post("/user/")
                .param("id", "1")
                .param("name", "testName");
        mvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string(equalTo("suc")));

        requestBuilder = get("/user/");
        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"testName\"}]")));
    }
}
