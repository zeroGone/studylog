package io.zerogone.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@WebFilter(description = "Check Login Information In Session",
        filterName = "Login Checker", urlPatterns = "/*")
@Order(1)
public class LoginCheckFilter implements Filter {
    private static final String LOGIN_PROPERTY_IN_SESSION = "userInfo";
    private static final String INDEX_URL = "/";

    private static final Log logger = LogFactory.getLog(LoginCheckFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Login checker is created");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        logger.debug("request uri : " + httpServletRequest.getRequestURI());

        if (hasUserInfoInSession(httpServletRequest.getSession())
                || isPossibleAccessForUnknown(httpServletRequest.getRequestURI())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletResponse.sendRedirect(INDEX_URL);
        }
    }

    private boolean hasUserInfoInSession(HttpSession httpSession) {
        return httpSession.getAttribute(LOGIN_PROPERTY_IN_SESSION) != null;
    }

    private boolean isPossibleAccessForUnknown(String requestUri) {
        return INDEX_URL.equals(requestUri);
    }

    @Override
    public void destroy() {
        logger.info("Login checker is destoried");
    }
}
