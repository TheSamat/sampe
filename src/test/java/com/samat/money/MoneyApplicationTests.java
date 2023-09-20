package com.samat.money;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
@TestPropertySource("/application-test.properties")
public class MoneyApplicationTests {
    @Autowired
    MoneyApplication moneyApplication;

    @Test
    @DisplayName("")
    public void myTest() {
        assertNotNull(moneyApplication);
    }
}