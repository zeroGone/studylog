package io.zerogone.converter;

import io.zerogone.model.dto.CommentDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Comment;
import io.zerogone.model.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityToDtoConverter implements Converter<Comment, CommentDto> {
    private final Converter<User, UserDto> converter;

    public CommentEntityToDtoConverter(Converter<User, UserDto> converter) {
        this.converter = converter;
    }

    @Override
    public CommentDto convert(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setPostId(comment.getPostId());
        dto.setContents(comment.getContents());
        dto.setWriter(converter.convert(comment.getWriter()));
        return dto;
    }
}
