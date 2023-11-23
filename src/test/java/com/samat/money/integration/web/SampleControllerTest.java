package com.samat.money.integration.web;

import com.samat.money.integration.AuthTestBase;
import com.samat.money.Domain.model.SampleRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class SampleControllerTest extends AuthTestBase {
    @Test
    @DisplayName("200 GET: /sample/")
    public void getAll() throws Exception {
        mockMvc.perform(get("/sample/")
                        .header("Authorization", "Bearer_" + userToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        ;
    }

    @Test
    @DisplayName("200 GET: /sample/list")
    public void getList() throws Exception {
        mockMvc.perform(get("/sample/list")
                        .header("Authorization", "Bearer_" + userToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        ;
    }

    @Test
    @DisplayName("200 GET: /sample/1")
    public void getById() throws Exception {
        mockMvc.perform(get("/sample/1")
                        .header("Authorization", "Bearer_" + userToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("404: GET: /sample/X")
    public void getById_404() throws Exception {
        mockMvc.perform(get("/sample/" + Short.MAX_VALUE)
                        .header("Authorization", "Bearer_" + userToken))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("200 POST: /sample/")
    public void create() throws Exception {
        SampleRequest sampleRequest = new SampleRequest("code", "name");

        mockMvc.perform(post("/sample/")
                        .header("Authorization", "Bearer_" + userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUtil.toJson(sampleRequest))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("200 PUT: /sample/1")
    public void update() throws Exception {
        SampleRequest sampleRequest = new SampleRequest("code", "name");

        mockMvc.perform(put("/sample/1")
                        .header("Authorization", "Bearer_" + userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUtil.toJson(sampleRequest))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("200 DELETE: /sample/2")
    public void remove() throws Exception {
        mockMvc.perform(delete("/sample/2")
                        .header("Authorization", "Bearer_" + userToken))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get("/sample/2")
                        .header("Authorization", "Bearer_" + userToken))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
