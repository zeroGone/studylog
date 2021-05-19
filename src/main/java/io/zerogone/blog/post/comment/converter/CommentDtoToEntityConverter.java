package io.zerogone.blog.post.comment.converter;

import io.zerogone.blog.post.comment.model.CommentDto;
import io.zerogone.blog.post.model.PostDto;
import io.zerogone.user.model.UserDto;
import io.zerogone.blog.post.comment.model.Comment;
import io.zerogone.blog.post.model.Post;
import io.zerogone.user.model.User;
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
