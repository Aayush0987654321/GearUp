package com.example.car_management;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiRequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger log =
            LoggerFactory.getLogger(ApiRequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        long startTime = System.nanoTime();

        try {
            filterChain.doFilter(request, response);
        } finally {
            long durationMs =
                    (System.nanoTime() - startTime) / 1_000_000;

            int status = response.getStatus();

            if (status >= 500) {
                log.error(
                        "HTTP {} {} -> {} ({} ms)",
                        request.getMethod(),
                        request.getRequestURI(),
                        status,
                        durationMs
                );
            } else if (status >= 400) {
                log.warn(
                        "HTTP {} {} -> {} ({} ms)",
                        request.getMethod(),
                        request.getRequestURI(),
                        status,
                        durationMs
                );
            } else {
                log.info(
                        "HTTP {} {} -> {} ({} ms)",
                        request.getMethod(),
                        request.getRequestURI(),
                        status,
                        durationMs
                );
            }
        }
    }
}