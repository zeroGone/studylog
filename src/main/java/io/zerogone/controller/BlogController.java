package io.zerogone.controller;

import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.vo.BlogVo;
import io.zerogone.service.BlogSearchService;
import io.zerogone.service.UserSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

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
            BlogVo blog = blogSearchService.getBlogVoByName(name);
            modelAndView.addObject("blog", blog);
            modelAndView.addObject("members", userSearchService.getUserVosByBlogVo(blog));
            modelAndView.setViewName("main");
        } catch (NotExistedDataException notExistedDataException) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
        }
        return modelAndView;
    }
}
