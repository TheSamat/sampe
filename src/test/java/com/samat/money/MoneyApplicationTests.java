package com.samat.money;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoneyApplicationTests {
    @Autowired
    MoneyApplication moneyApplication;

    @Test
    @DisplayName("Инициализация приложения")
    public void myTest() {
        assertNotNull(moneyApplication);
    }
}