package io.zerogone.controller.api;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.PostDto;
import io.zerogone.model.dto.UserWithBlogsDto;
import io.zerogone.model.entity.Post;
import io.zerogone.service.create.CreateTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Objects;

@RestController
public class PostCreateController {
    private final CreateTemplate<Post> createTemplate;

    public PostCreateController(CreateTemplate<Post> createTemplate) {
        this.createTemplate = createTemplate;
    }

    @PostMapping("api/post")
    public ResponseEntity<PostDto> handlePostCreateApi(@SessionAttribute UserWithBlogsDto userInfo,
                                                       @RequestBody PostDto post) {

        post.setWriter(userInfo);
        post.setBlog(getTargetBlog(userInfo, post.getBlog()));

        return new ResponseEntity<>((PostDto) createTemplate.create(post), HttpStatus.CREATED);
    }

    private BlogDto getTargetBlog(UserWithBlogsDto writer, BlogDto blogDto) {
        BlogDto targetBlog = writer.getBlogs()
                .stream()
                .filter(blog -> Objects.equals(blog.getName(), blogDto.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);

        blogDto.setId(targetBlog.getId());
        return blogDto;
    }
}
