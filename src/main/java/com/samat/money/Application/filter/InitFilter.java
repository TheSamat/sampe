package com.samat.money.Application.filter;

import com.samat.money.Application.exceprion.CustomError;
import com.samat.money.Application.exceprion.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class InitFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) {
        try {
            httpServletRequest.setAttribute("requestId", UUID.randomUUID());
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            throw new CustomException(CustomError.SERVLET_EXCEPTION);
        }
    }
}
