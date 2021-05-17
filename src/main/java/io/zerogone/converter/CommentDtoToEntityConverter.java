package io.zerogone.converter;

import io.zerogone.model.dto.CommentDto;
import io.zerogone.model.dto.PostDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Comment;
import io.zerogone.model.entity.Post;
import io.zerogone.model.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoToEntityConverter implements Converter<CommentDto, Comment> {
    private final Converter<UserDto, User> userConverter;

    public CommentDtoToEntityConverter(
            Converter<UserDto, User> userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public Comment convert(CommentDto dto) {
        return new Comment(dto.getId(),
                dto.getContents(),
                convert(dto.getPost()),
                userConverter.convert(dto.getWriter()));
    }

    private Post convert(PostDto dto) {
        return new Post(dto.getId(), dto.getTitle(), dto.getContents(), null, null);
    }
}
