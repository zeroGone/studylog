package io.zerogone.controller.api;

import io.zerogone.model.dto.CommentDto;
import io.zerogone.model.dto.PostDto;
import io.zerogone.user.model.UserDto;
import io.zerogone.service.create.CreateService;
import io.zerogone.service.search.SearchService;
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
