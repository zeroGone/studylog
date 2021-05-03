package io.zerogone.controller.api;

import io.zerogone.model.dto.BlogCreateDto;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.UserWithBlogsDto;
import io.zerogone.service.create.CreateWithImageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class BlogCreateController {
    private final CreateWithImageService createWithImageService;

    public BlogCreateController(@Qualifier("blogCreateService") CreateWithImageService createWithImageService) {
        this.createWithImageService = createWithImageService;
    }

    @PostMapping("api/blog")
    public ResponseEntity<BlogDto> handleBlogCreateApi(@SessionAttribute UserWithBlogsDto userInfo,
                                                       @ModelAttribute BlogCreateDto blogDto,
                                                       @RequestPart(required = false) MultipartFile image) {
        blogDto.setAdmin(userInfo);

        BlogDto responseDto;
        if (image == null) {
            responseDto = (BlogDto) createWithImageService.create(blogDto);
        } else {
            responseDto = (BlogDto) createWithImageService.create(blogDto, image);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
