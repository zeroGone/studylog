package io.zerogone.controller.api;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.PostDto;
import io.zerogone.model.dto.UserDto;
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
    private final CreateService<PostDto> createService;

    public PostCreateController(CreateService<PostDto> createService) {
        this.createService = createService;
    }

    @PostMapping("api/post")
    public ResponseEntity<PostDto> handlePostCreateApi(@SessionAttribute UserDto userInfo,
                                                       @RequestBody PostDto post) {

        post.setWriter(userInfo);
        post.setBlog(getTargetBlog(userInfo, post.getBlog()));

        return new ResponseEntity<>(createService.create(post), HttpStatus.CREATED);
    }

    private BlogDto getTargetBlog(UserDto writer, BlogDto blogDto) {
        BlogDto targetBlog = writer.getBlogs()
                .stream()
                .filter(blog -> Objects.equals(blog.getName(), blogDto.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);

        blogDto.setId(targetBlog.getId());
        return blogDto;
    }
}
