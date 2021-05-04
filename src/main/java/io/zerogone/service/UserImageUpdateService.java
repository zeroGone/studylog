package io.zerogone.service;

import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import io.zerogone.service.fileupload.ImageUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class UserImageUpdateService {
    private final ImageUploadService<UserDto> imageUploadService;
    private final UserDao userDao;

    public UserImageUpdateService(ImageUploadService<UserDto> imageUploadService, UserDao userDao) {
        this.imageUploadService = imageUploadService;
        this.userDao = userDao;
    }

    @Transactional
    public UserDto updateUserImage(UserDto user, MultipartFile imageFile) {
        user = imageUploadService.upload(user, imageFile);
        User entity = new User(user.getId(), user.getName(), user.getEmail(), user.getNickName(), user.getImageUrl());
        userDao.updateImageUrl(entity);
        return user;
    }
}
