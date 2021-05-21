package io.zerogone.user;

import ch.qos.logback.classic.Logger;
import io.zerogone.user.model.UserDto;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;

@WebFilter(urlPatterns = {"/logout", "/user/{id}"})
public class LoginCheckFilter implements Filter {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("init login check filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("filt this request has login info");

        UserDto userInfo = getUserInfo(servletRequest);

        if (hasLoginUserInfo(userInfo)) {
            logger.info("this request has user info!");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.setHeader("location", "/unautorized");
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private UserDto getUserInfo(ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        return (UserDto) session.getAttribute("userInfo");
    }

    private boolean hasLoginUserInfo(UserDto userInfo) {
        if (userInfo == null) {
            return false;
        }
        Set<ConstraintViolation<UserDto>> constraintViolationSet = validator.validate(userInfo);
        return constraintViolationSet.isEmpty();
    }

    @Override
    public void destroy() {
        logger.info("destory login check filter");
    }
}
