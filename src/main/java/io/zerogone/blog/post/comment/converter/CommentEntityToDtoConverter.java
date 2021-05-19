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
