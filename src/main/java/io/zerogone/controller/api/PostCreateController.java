package io.zerogone.controller.api;

import io.zerogone.model.BlogDto;
import io.zerogone.model.vo.BlogVo;
import io.zerogone.model.vo.UserVo;
import io.zerogone.model.dto.PostCreateDto;
import io.zerogone.service.create.CreateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Objects;

@RestController
public class PostCreateController {
    private final CreateService createService;

    public PostCreateController(CreateService createService) {
        this.createService = createService;
    }

    @PostMapping("api/post")
    public ResponseEntity<Object> handlePostCreateApi(@SessionAttribute UserVo userInfo,
                                                      @RequestBody PostCreateDto postCreateDto) {

        postCreateDto.setWriter(userInfo);
        postCreateDto.setBlog(getTargetBlog(userInfo, postCreateDto.getBlog()));

        return new ResponseEntity<>(createService.createEntity(postCreateDto), HttpStatus.CREATED);
    }

    private BlogDto getTargetBlog(UserVo writer, BlogDto blogDto) {
        BlogVo targetBlogVo = writer.getBlogs()
                .stream()
                .filter(blogVo -> Objects.equals(blogVo.getName(), blogDto.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);

        blogDto.setId(targetBlogVo.getId());
        return blogDto;
    }
}
