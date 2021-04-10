package io.zerogone.service;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.UserDto;
import io.zerogone.model.UserVo;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@Service
public class UserCreateService {
    private static final String USER_IMAGE_FILE_PATH = "img/user";
    private static final String USER_DEFAULT_IMAGE_URL = "/img/user-default/";
    private static final String USER_DEFAULT_IMAGE_TYPE = ".png";

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final FileUploadService fileUploadService;
    private final UserDao userDao;

    public UserCreateService(FileUploadService fileUploadService, UserDao userDao) {
        this.fileUploadService = fileUploadService;
        this.userDao = userDao;
    }

    @Transactional
    public UserVo createUser(UserDto userDto, MultipartFile imageFile) {
        String userImageUrl = uploadUserImage(imageFile, userDto.getImageUrl());

        User user = new User(0,
                userDto.getName(),
                userDto.getEmail(),
                userDto.getNickName(),
                userImageUrl);

        try {
            userDao.save(user);
        } catch (PersistenceException persistenceException) {
            logger.error("user property is duplicated ! " + persistenceException.getMessage());
            throw new UniquePropertyException("유저의 이메일이나 닉네임이 중복되었습니다");
        }

        return new UserVo(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getNickName(),
                user.getImageUrl(),
                user.getCreateDateTime(),
                user.getUpdateDateTime());
    }

    private String uploadUserImage(MultipartFile imageFile, String snsImageUrl) {
        if (snsImageUrl != null) {
            return snsImageUrl;
        }

        String uploadedImageUrl = fileUploadService.uploadFile(USER_IMAGE_FILE_PATH, imageFile);

        if (uploadedImageUrl == null) {
            int randomNumber = (int) (Math.random() * 10);
            return USER_DEFAULT_IMAGE_URL + randomNumber + USER_DEFAULT_IMAGE_TYPE;
        } else {
            return uploadedImageUrl;
        }
    }
}
