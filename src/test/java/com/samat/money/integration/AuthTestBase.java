package com.samat.money.integration;

import com.samat.money.model.SampleRequest;
import com.samat.money.util.JsonUtil;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@FieldDefaults(level = AccessLevel.PROTECTED)
public class AuthTestBase {
    String userToken = null;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    JsonUtil jsonUtil;

    @Test
    @DisplayName("200 POST: /auth/")
    public void authUser() throws Exception {
        //TODO: подставить запрос авторизации
        SampleRequest sampleRequest = new SampleRequest("code", "name");

        MvcResult result = mockMvc.perform(post("/sample/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUtil.toJson(sampleRequest))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        this.userToken = new JSONObject(result.getResponse().getContentAsString()).getString("token");
    }
}
