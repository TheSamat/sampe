package com.samat.money.Application.exceprion;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private ExceptionResponse response;
    private HttpStatus status;

    public CustomException(CustomError error) {
        this.response = new ExceptionResponse(error);
        this.status = error.getStatus();
    }

    @Override
    public String getMessage() {
        return response.getMessage();
    }
}
