package io.zerogone.blog.post.converter;

import io.zerogone.blog.post.model.PostDto;
import io.zerogone.blog.post.model.Post;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostEntityToDtoConverter implements Converter<Post, PostDto> {
    @Override
    public PostDto convert(Post entity) {
        PostDto postDto = new PostDto();
        postDto.setId(entity.getId());
        postDto.setTitle(entity.getTitle());
        postDto.setContents(entity.getContents());
        postDto.setHits(entity.getHits());
        postDto.setCreateDate(entity.getCreateDateTime().toLocalDate());
        postDto.setUpdateDate(entity.getUpdateDateTime().toLocalDate());
        return postDto;
    }
}
