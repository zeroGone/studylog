package io.zerogone.blog.controller;

import io.zerogone.blog.service.BlogSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NoResultException;

@Controller
public class BlogController {
    private final BlogSearchService blogSearchService;

    public BlogController(BlogSearchService blogSearchService) {
        this.blogSearchService = blogSearchService;
    }

    @GetMapping("{name}")
    public ModelAndView handleBlogMainPage(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject("blog", blogSearchService.getBlog(name));
            modelAndView.setViewName("main");
        } catch (NoResultException noResultException) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
        }
        return modelAndView;
    }
}
