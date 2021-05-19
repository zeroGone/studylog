package io.zerogone.user.service;

import io.zerogone.user.model.UserDto;
import io.zerogone.user.model.User;
import io.zerogone.user.UserDao;
import io.zerogone.service.fileupload.ImageUploadService;
import io.zerogone.service.fileupload.ImageUrl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class UserImageUpdateService {
    private final ImageUploadService imageUploadService;
    private final UserDao userDao;

    public UserImageUpdateService(@Qualifier("userImageUploadService") ImageUploadService imageUploadService,
                                  UserDao userDao) {
        this.imageUploadService = imageUploadService;
        this.userDao = userDao;
    }

    @Transactional
    public UserDto updateUserImage(UserDto user, MultipartFile imageFile) {
        ImageUrl newImageUrl = imageUploadService.upload(imageFile);
        User entity = new User(user.getId(), user.getName(), user.getEmail(), user.getNickName(), newImageUrl.getValue());
        userDao.updateImageUrl(entity);
        return user;
    }
}
