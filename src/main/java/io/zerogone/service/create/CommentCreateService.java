package io.zerogone.service.create;

import io.zerogone.model.dto.CommentDto;
import io.zerogone.model.entity.Comment;
import io.zerogone.repository.CommentDao;
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
