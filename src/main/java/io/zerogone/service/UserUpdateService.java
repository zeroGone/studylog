package io.zerogone.service;

import io.zerogone.model.UserDto;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class UserUpdateService {
    private static final String USER_IMAGE_FILE_PATH = "img/user";

    private final UserDao userDao;
    private final FileUploadService fileUploadService;

    public UserUpdateService(UserDao userDao, FileUploadService fileUploadService) {
        this.userDao = userDao;
        this.fileUploadService = fileUploadService;
    }

    @Transactional
    public void updateUserImage(UserDto user, MultipartFile imageFile) {
        String savedImgUrl = fileUploadService.uploadFile(USER_IMAGE_FILE_PATH, imageFile);
        user.setImgUrl(savedImgUrl);
        userDao.update(new User(user));
    }
}
