package io.zerogone.service.search;

import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserEmailSearchService implements SearchService<String, UserDto> {
    private final UserDao userDao;

    public UserEmailSearchService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDto search(String key) {
        User entity = userDao.findByEmail(key);
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setNickName(entity.getNickName());
        dto.setImageUrl(entity.getImageUrl());
        return dto;
    }
}
