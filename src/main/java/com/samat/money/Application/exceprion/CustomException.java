package com.samat.money.Application.exceprion;

import com.samat.money.Domain.constant.Error;
import com.samat.money.Domain.constant.ErrorCode;
import com.samat.money.Domain.model.response.ErrorResponse;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private HttpStatus status;
    private ErrorCode code;
    private String message;
    private Logger logger;

    public CustomException(Error error, Logger logger) {
        this.status = error.getStatus();
        this.code = error.getCode();
        this.message = error.getMessage();
        this.logger = logger;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
