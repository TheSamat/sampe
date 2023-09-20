package com.samat.money.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@FieldDefaults(level = AccessLevel.PRIVATE)
@TestPropertySource("/application-test.properties")
public class SampleControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    SampleController controller;

    @Test
    @DisplayName("")
    @BeforeAll
    public void notNull() {
        assertNotNull(controller);
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/sample/"))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }
}
