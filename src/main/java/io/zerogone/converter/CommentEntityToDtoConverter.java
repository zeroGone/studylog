package io.zerogone.converter;

import io.zerogone.model.dto.CommentDto;
import io.zerogone.model.dto.PostDto;
import io.zerogone.user.model.UserDto;
import io.zerogone.model.entity.Comment;
import io.zerogone.model.entity.Post;
import io.zerogone.user.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityToDtoConverter implements Converter<Comment, CommentDto> {
    private final Converter<User, UserDto> userConverter;
    private final Converter<Post, PostDto> postConverter;

    public CommentEntityToDtoConverter(Converter<User, UserDto> userConverter, Converter<Post, PostDto> postConverter) {
        this.userConverter = userConverter;
        this.postConverter = postConverter;
    }

    @Override
    public CommentDto convert(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setPost(postConverter.convert(comment.getPost()));
        dto.setContents(comment.getContents());
        dto.setWriter(userConverter.convert(comment.getWriter()));
        dto.setCreateDate(comment.getCreateDateTime().toLocalDate());
        return dto;
    }
}
