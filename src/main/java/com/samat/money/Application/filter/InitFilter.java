package com.samat.money.Application.filter;

import com.samat.money.Application.exceprion.CustomException;
import com.samat.money.Domain.constant.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class InitFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) {
        try {
            httpServletRequest.setAttribute("requestId", UUID.randomUUID());
            log.info(httpServletRequest.getAttribute("requestId") + "\t" + convertToCurl(httpServletRequest));
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            throw new CustomException(Error.SERVLET_EXCEPTION, log);
        }
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
        curlCommand.append(url);

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
