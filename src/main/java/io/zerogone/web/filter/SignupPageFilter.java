package io.zerogone.web.filter;

import ch.qos.logback.classic.Logger;
import io.zerogone.domain.LoginRequestForm;
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

@WebFilter("/signup")
public class SignupPageFilter implements Filter {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("init signup page filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("-----Filt signup page start-----");
        LoginRequestForm visitor = getVisitor(servletRequest);
        if (isAccessibleVisitor(visitor)) {
            logger.info("-----Filt signup page end-----");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.setHeader("location", "/unautorized");
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private LoginRequestForm getVisitor(ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        return (LoginRequestForm) session.getAttribute("visitor");
    }

    private boolean isAccessibleVisitor(LoginRequestForm visitor) {
        if (visitor == null) {
            return false;
        }
        Set<ConstraintViolation<LoginRequestForm>> constraintViolationSet = validator.validate(visitor);
        return constraintViolationSet.isEmpty();
    }


    @Override
    public void destroy() {
        logger.info("destory signup page filter");
    }
}