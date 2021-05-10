package io.zerogone.controller.api;

import io.zerogone.model.dto.CommentDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.service.create.CreateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
public class CommentCreateController {
    private final CreateService<CommentDto> createService;

    public CommentCreateController(CreateService<CommentDto> createService) {
        this.createService = createService;
    }

    @PostMapping("api/comment")
    public ResponseEntity<CommentDto> handleCreatingCommentApi(
            @SessionAttribute UserDto userInfo,
            @RequestBody CommentDto comment) {

        comment.setWriter(userInfo);
        return new ResponseEntity<>(createService.create(comment), HttpStatus.CREATED);
    }
}
