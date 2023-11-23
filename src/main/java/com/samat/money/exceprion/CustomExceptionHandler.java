package com.samat.money.exceprion;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleException(
            CustomException e,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        errorLog(e, request, response);
        return new ResponseEntity<>(e.getResponse(), e.getStatus());
    }

    //TODO: Ошибка валидации

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException e) {
//        log.error(e.getMessage(), e);
//        return new ResponseEntity<>(new CustomException(CustomError.))
//    }


    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<ExceptionResponse> handleException(
            HibernateException e,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        errorLog(e, httpServletRequest, httpServletResponse);
        ExceptionResponse response = new ExceptionResponse(e.getLocalizedMessage(), "Ошибка базы данных");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void errorLog(Exception e, HttpServletRequest request, HttpServletResponse response) {
        String row = String.format(
                "%s\tError:%s --- %s",
                request.getAttribute("requestId"),
                e.toString(),
                convertToCurl(request)
        );

        log.error(row, e);
    }

    public static String convertToCurl(HttpServletRequest request) {
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Enumeration<String> headerNames = request.getHeaderNames();
        List<String> headers = Collections.list(headerNames);

        // Создаем строку curl-запроса
        StringBuilder curlCommand = new StringBuilder("curl");

        // Добавляем метод запроса
        curlCommand.append(" -X ").append(method);

        // Добавляем URL
        curlCommand.append(" \\'").append(url).append("\\'");

        // Добавляем параметры запроса (если есть)
        if (!parameterMap.isEmpty()) {
            String parameters = parameterMap.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + String.join(",", entry.getValue()))
                    .collect(Collectors.joining("&"));
            curlCommand.append(" -d '").append(parameters).append("'");
        }

        // Добавляем заголовки запроса
        for (String header : headers) {
            String headerValue = request.getHeader(header);
            curlCommand.append(" -H '").append(header).append(": ").append(headerValue).append("'");
        }

        return curlCommand.toString();
    }
}
