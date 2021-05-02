package io.zerogone.converter;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.BlogWithMembersDto;
import io.zerogone.model.dto.DataTransferObject;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Blog;
import org.hibernate.LazyInitializationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class BlogConverter implements Converter<Blog> {
    @Override
    public Blog convert(DataTransferObject dto) {
        BlogDto blogDto = (BlogDto) dto;
        return new Blog(blogDto.getId(), blogDto.getName(), blogDto.getIntroduce(), blogDto.getImageUrl());
    }

    @Override
    public DataTransferObject convert(Blog entity) {
        BlogWithMembersDto dto = new BlogWithMembersDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setIntroduce(entity.getIntroduce());
        dto.setImageUrl(entity.getImageUrl());
        try {
            dto.setMembers(entity.getMembers()
                    .stream()
                    .map(user -> {
                        UserDto userDto = new UserDto();
                        userDto.setId(user.getId());
                        userDto.setName(user.getName());
                        userDto.setEmail(user.getEmail());
                        userDto.setNickName(user.getNickName());
                        userDto.setImageUrl(user.getImageUrl());
                        return userDto;
                    }).collect(Collectors.toList()));
        } catch (LazyInitializationException lazyInitializationException) {
            dto.setMembers(new ArrayList<>());
        }
        return dto;
    }
}
