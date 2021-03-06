package io.zerogone.blog.post.comment.controller;

import io.zerogone.blog.post.comment.model.CommentDto;
import io.zerogone.blog.post.model.PostDto;
import io.zerogone.user.model.UserDto;
import io.zerogone.common.service.CreateService;
import io.zerogone.common.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@Validated
public class CommentCreateController {
    private final CreateService<CommentDto> createService;
    private final SearchService<Integer, PostDto> searchService;

    public CommentCreateController(CreateService<CommentDto> createService,
                                   SearchService<Integer, PostDto> searchService) {
        this.createService = createService;
        this.searchService = searchService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("blogs/{blogId}/posts/{postId}/comments")
    public CommentDto handleCreatingCommentApi(@SessionAttribute @Valid UserDto userInfo,
                                               @RequestBody @Valid CommentDto comment,
                                               @PathVariable @Positive Integer postId) {

        comment.setWriter(userInfo);
        comment.setPost(searchService.search(postId));
        return createService.create(comment);
    }
}
