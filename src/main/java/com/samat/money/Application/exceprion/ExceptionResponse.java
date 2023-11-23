package com.samat.money.Application.exceprion;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

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
