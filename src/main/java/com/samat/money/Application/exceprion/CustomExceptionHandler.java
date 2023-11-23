package com.samat.money.Application.exceprion;

import com.samat.money.Domain.constant.ErrorCode;
import com.samat.money.Domain.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleException(
            CustomException e,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        errorLog(e, request, response);
        ErrorResponse result = new ErrorResponse(
                UUID.fromString(request.getAttribute("requestId").toString()),
                e.getCode(),
                e.getMessage());
        return new ResponseEntity<>(result, e.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException e,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response) {
        errorLog(e, request, response);
        ErrorResponse result = new ErrorResponse(
                UUID.fromString(request.getAttribute("requestId").toString()),
                ErrorCode.REQUEST_VALIDATION_ERROR,
                e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response) {
        errorLog(e, request, response);
        ErrorResponse result = new ErrorResponse(
                UUID.fromString(request.getAttribute("requestId").toString()),
                ErrorCode.INTERNAL_SERVER_ERROR,
                e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<ErrorResponse> handleException(
            HibernateException e,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        errorLog(e, request, response);
        ErrorResponse result = new ErrorResponse(
                UUID.fromString(request.getAttribute("requestId").toString()),
                ErrorCode.DATABASE_ERROR,
                e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    private void errorLog(CustomException e, HttpServletRequest request, HttpServletResponse response) {
        String row = String.format(
                "%s\t%s",
                request.getAttribute("requestId"),
                e.getMessage()
        );

        e.getLogger().error(row);
    }

    private void errorLog(Exception e, HttpServletRequest request, HttpServletResponse response) {
        String row = String.format(
                "%s\t%s",
                request.getAttribute("requestId"),
                e.getMessage()
        );

        log.error(row, e);
    }
}
