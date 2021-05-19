package io.zerogone.blog.post.controller;

import io.zerogone.user.model.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;

@Controller
@Validated
public class PostWriteController {
    @GetMapping("{name}/posts/new")
    public String getPostWriteViewName(@SessionAttribute @Valid UserDto userInfo) {
        return "post_write";
    }
}
