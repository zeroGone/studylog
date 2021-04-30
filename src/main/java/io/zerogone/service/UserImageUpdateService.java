package io.zerogone.service;

import io.zerogone.model.vo.UserVo;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class UserImageUpdateService {
    private static final String USER_IMAGE_FILE_PATH = "img/user";

    private final UserDao userDao;
    private final FileUploadService fileUploadService;

    public UserImageUpdateService(UserDao userDao, FileUploadService fileUploadService) {
        this.userDao = userDao;
        this.fileUploadService = fileUploadService;
    }

    @Transactional
    public UserVo updateUserImage(UserVo user, MultipartFile imageFile) {
        String savedImgUrl = fileUploadService.uploadFile(USER_IMAGE_FILE_PATH, imageFile);

        User entity = new User(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getNickName(),
                savedImgUrl);

        userDao.updateImageUrl(entity);

        return new UserVo(entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getNickName(),
                entity.getImageUrl());
    }
}
