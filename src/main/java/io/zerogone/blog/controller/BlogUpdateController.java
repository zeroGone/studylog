package io.zerogone.blog.controller;

import io.zerogone.common.exception.NotAuthorizedAccessException;
import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.model.MemberRole;
import io.zerogone.blog.service.BlogUpdateService;
import io.zerogone.common.service.SearchService;
import io.zerogone.user.model.UserDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;
import java.util.Objects;

@RestController
@Validated
public class BlogUpdateController {
    private final BlogUpdateService updateService;
    private final SearchService<Integer, BlogDto> searchService;

    public BlogUpdateController(BlogUpdateService updateService, SearchService<Integer, BlogDto> searchService) {
        this.updateService = updateService;
        this.searchService = searchService;
    }

    @PutMapping("blogs/{id}")
    public BlogDto handleUpdatingBlog(@SessionAttribute UserDto userInfo,
                                      @PathVariable @Positive Integer id,
                                      @RequestBody BlogDto blog) {

        validateAuth(userInfo, blog);

        return updateService.updateBlog(blog);
    }

    @PostMapping("blogs/{id}")
    public BlogDto handleUpdatingBlogWithImage(@SessionAttribute UserDto userInfo,
                                               @PathVariable @Positive Integer id,
                                               @ModelAttribute BlogDto blog,
                                               MultipartFile image) {

        validateAuth(userInfo, blog);

        return updateService.updateBlog(blog, image);
    }

    private void validateAuth(UserDto user, BlogDto blog) {
        blog.getMembers()
                .stream()
                .filter(member -> Objects.equals(member.getId(), user.getId()) && Objects.equals(member.getRole(), MemberRole.ADMIN))
                .findAny()
                .orElseThrow(new NotAuthorizedAccessException("관리자가 아닙니다!"));
    }
}
