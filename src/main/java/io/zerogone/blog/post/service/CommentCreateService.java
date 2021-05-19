package io.zerogone.blog.post.service;

import io.zerogone.blog.post.comment.model.CommentDto;
import io.zerogone.blog.post.comment.model.Comment;
import io.zerogone.blog.post.comment.CommentDao;
import io.zerogone.common.service.CreateService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentCreateService implements CreateService<CommentDto> {
    private final CommentDao commentDao;
    private final ConversionService conversionService;

    public CommentCreateService(CommentDao commentDao, ConversionService conversionService) {
        this.commentDao = commentDao;
        this.conversionService = conversionService;
    }

    @Transactional
    @Override
    public CommentDto create(CommentDto dto) {
        Comment entity = conversionService.convert(dto, Comment.class);
        commentDao.save(entity);
        return conversionService.convert(entity, CommentDto.class);
    }
}
