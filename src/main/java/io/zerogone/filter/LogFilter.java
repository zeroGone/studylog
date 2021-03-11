package io.zerogone.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(description = "Filter for Log",
        filterName = "Log Filter", urlPatterns = "*")
@Order(1)
public class LogFilter extends OncePerRequestFilter {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.info("Request URL path : "
                + httpServletRequest.getRequestURI());

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
