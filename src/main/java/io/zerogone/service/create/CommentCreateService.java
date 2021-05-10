package io.zerogone.service.create;

import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.model.dto.CommentDto;
import io.zerogone.model.entity.Comment;
import io.zerogone.repository.CommentDao;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentCreateService implements CreateService<CommentDto> {
    private final CommentDao commentDao;
    private final Converter<Comment, CommentDto> entityConverter;
    private final Converter<CommentDto, Comment> dtoConverter;

    public CommentCreateService(CommentDao commentDao,
                                Converter<Comment, CommentDto> entityConverter,
                                Converter<CommentDto, Comment> dtoConverter) {
        this.commentDao = commentDao;
        this.entityConverter = entityConverter;
        this.dtoConverter = dtoConverter;
    }

    @Transactional
    @Override
    public CommentDto create(CommentDto dto) {
        validate(dto);
        Comment entity = dtoConverter.convert(dto);
        commentDao.save(entity);
        return entityConverter.convert(entity);
    }

    private void validate(CommentDto dto) {
        if (dto.getContents() == null) {
            throw new NotNullPropertyException(Comment.class, "contents");
        }
    }
}
