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
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(description = "Check Login Information In Session",
//        filterName = "Login Checker", urlPatterns = "*")
//@Order(2)
public class LoginCheckFilter extends OncePerRequestFilter {
    private static final String LOGIN_PROPERTY_IN_SESSION = "userInfo";
    private static final String INDEX_URL = "/";
    private static final String SWAGGER_URL = "/swagger-ui.html";

    private static final Log logger = LogFactory.getLog(LoginCheckFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (hasUserInfoInSession(httpServletRequest.getSession())
                || isPossibleAccessForUnknown(httpServletRequest.getRequestURI())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            logger.info("Unknown access");
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
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return isWebResources(request.getRequestURI());
    }

    private boolean isWebResources(String uri) {
        return uri.matches("\\/css\\/.*\\.css|" +
                "\\/js\\/.*\\.js|" +
                "\\/img\\/.*\\.jpg|" +
                "\\/img\\/.*\\.png|" +
                "\\/img\\/logo.ico");
    }
}
