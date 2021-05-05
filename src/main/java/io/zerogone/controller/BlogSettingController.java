package io.zerogone.controller;

import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.service.search.SearchService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogSettingController {
    private final SearchService<String, BlogDto> searchService;

    public BlogSettingController(@Qualifier("blogWithMembersSearchService") SearchService<String, BlogDto> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("{name}/setting")
    public ModelAndView handleBlogSettingPage(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject("blog", searchService.search(name));
            modelAndView.setViewName("blog_setting");
        } catch (NotExistedDataException notExistedDataException) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
        }
        return modelAndView;
    }
}