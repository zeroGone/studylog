package io.zerogone.controller;

import io.zerogone.model.BlogName;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.service.search.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@Validated
public class BlogSettingController {
    private final SearchService<BlogName, BlogDto> searchService;

    public BlogSettingController(SearchService<BlogName, BlogDto> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("{name}/setting")
    public ModelAndView handleBlogSettingPage(@PathVariable @Valid BlogName name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("blog", searchService.search(name));
        modelAndView.setViewName("blog_setting");
        return modelAndView;
    }
}
