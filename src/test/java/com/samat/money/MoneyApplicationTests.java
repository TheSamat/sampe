package com.samat.money;

import com.samat.money.exceprion.CustomError;
import com.samat.money.exceprion.CustomException;
import liquibase.Liquibase;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

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