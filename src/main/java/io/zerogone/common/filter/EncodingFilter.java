package io.zerogone.common.filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(description = "Filter for encoding",
        filterName = "encoder", urlPatterns = "*")
@Order(2)
public class EncodingFilter extends OncePerRequestFilter {
    private static final String UTF8 = "UTF-8";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding(UTF8);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
