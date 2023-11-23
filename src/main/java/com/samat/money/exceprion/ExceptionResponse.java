package com.samat.money.exceprion;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionResponse {
    String message;
    String title;

    public ExceptionResponse(CustomError error) {
        this.message = error.getMessage();
        this.title = error.getTitle();
    }
}
