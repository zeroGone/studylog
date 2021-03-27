package io.zerogone.blog.controller;

import io.zerogone.blog.model.BlogVo;
import io.zerogone.blog.service.BlogSearchService;
import io.zerogone.user.service.UserSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NoResultException;

@Controller
public class BlogController {
    private final BlogSearchService blogSearchService;
    private final UserSearchService userSearchService;

    public BlogController(BlogSearchService blogSearchService, UserSearchService userSearchService) {
        this.blogSearchService = blogSearchService;
        this.userSearchService = userSearchService;
    }

    @GetMapping("{name}")
    public ModelAndView handleBlogMainPage(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            BlogVo blog = blogSearchService.getBlog(name);
            modelAndView.addObject("blog", blog);
            modelAndView.addObject("members", userSearchService.getUsersByBlogId(blog.getId()));
            modelAndView.setViewName("main");
        } catch (NoResultException noResultException) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
        }
        return modelAndView;
    }
}
