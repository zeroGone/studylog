package io.zerogone.controller.api;

import io.zerogone.exception.NotAuthorizedException;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.PostDto;
import io.zerogone.user.model.UserDto;
import io.zerogone.service.create.CreateService;
import io.zerogone.service.search.SearchService;
import io.zerogone.validator.NewEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Objects;

@RestController
@Validated
public class PostCreateController {
    private final CreateService<PostDto> createService;
    private final SearchService<Integer, BlogDto> searchService;

    public PostCreateController(CreateService<PostDto> createService,
                                SearchService<Integer, BlogDto> searchService) {
        this.createService = createService;
        this.searchService = searchService;
    }

    @PostMapping("blogs/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto handleCreatingPost(@SessionAttribute @Valid UserDto userInfo,
                                      @PathVariable @Positive Integer id,
                                      @RequestBody @Validated(NewEntity.class) PostDto post) {

        BlogDto targetBlog = searchService.search(id);
        validateWritingAuth(userInfo, targetBlog);

        post.setWriter(userInfo);
        post.setBlog(targetBlog);

        return createService.create(post);
    }

    private void validateWritingAuth(UserDto userInfo, BlogDto targetBlog) {
        userInfo.getBlogs()
                .stream()
                .filter(userBlog -> Objects.equals(userBlog.getId(), targetBlog.getId()))
                .findAny()
                .orElseThrow(new NotAuthorizedException("소속되지 않은 블로그입니다"));
    }
}
