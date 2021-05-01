package io.zerogone.controller;

import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.dto.BlogWithMembersDto;
import io.zerogone.service.search.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogController {
    private final SearchService<String, BlogWithMembersDto> searchService;

    public BlogController(SearchService<String, BlogWithMembersDto> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("{name}")
    public ModelAndView handleBlogMainPage(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject("blog", searchService.search(name));
            modelAndView.setViewName("main");
        } catch (NotExistedDataException notExistedDataException) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
        }
        return modelAndView;
    }
}
