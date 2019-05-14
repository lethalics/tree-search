package com.holidu.interview.assignment.controller;


import com.holidu.interview.assignment.service.TreeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TreeController.class)
public class TreeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TreeService service;

    @Test
    public void testSearchTrees() throws Exception {
        Map<String, Long> searchResult = new HashMap<>();
        searchResult.put("cherry", 55L);
        when(service.search(anyDouble(), anyDouble(), anyDouble())).thenReturn(searchResult);

        mvc.perform(post("/api/v1/trees/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"radius\" : 500.10, \"x\": -73, \"y\": 40}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.cherry").value(55));
    }

    @Test
    public void testInvalidInputRadius() throws Exception {
        mvc.perform(post("/api/v1/trees/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"radius\" : -1, \"x\": -73, \"y\": 40}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testInvalidInputX() throws Exception {
        mvc.perform(post("/api/v1/trees/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"radius\" : 200.20, \"x\": -181, \"y\": 40}"))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testInvalidInputY() throws Exception {
        mvc.perform(post("/api/v1/trees/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"radius\" : 200.20, \"x\": -90, \"y\": 140}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testInputNullRadius() throws Exception {
        mvc.perform(post("/api/v1/trees/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"x\": -90, \"y\": 40}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testInputNullX() throws Exception {
    mvc.perform(
            post("/api/v1/trees/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"radius\" : 200.20,  \"y\": 40}"))
        .andExpect(status().isBadRequest());
    }

    @Test
    public void testInputNullY() throws Exception {
    mvc.perform(
            post("/api/v1/trees/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"radius\" : 200.20,  \"x\": -90,}"))
        .andExpect(status().isBadRequest());
    }
}