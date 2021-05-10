package io.zerogone.converter;

import io.zerogone.model.dto.CommentDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Comment;
import io.zerogone.model.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoToEntityConverter implements Converter<CommentDto, Comment> {
    private final Converter<UserDto, User> converter;

    public CommentDtoToEntityConverter(Converter<UserDto, User> converter) {
        this.converter = converter;
    }

    @Override
    public Comment convert(CommentDto dto) {
        return new Comment(dto.getId(), dto.getContents(), dto.getPostId(), converter.convert(dto.getWriter()));
    }
}
