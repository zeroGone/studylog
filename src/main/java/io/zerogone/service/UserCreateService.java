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
    private static final String imageFilePath = "img/user";

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final FileUploadService fileUploadService;
    private final UserDao userDao;

    public UserCreateService(FileUploadService fileUploadService, UserDao userDao) {
        this.fileUploadService = fileUploadService;
        this.userDao = userDao;
    }

    @Transactional
    public UserVo createUser(UserDto userDto, MultipartFile imageFile) {
        if (imageFile != null) {
            String uploadedImgUrl = fileUploadService.uploadFile(imageFilePath, imageFile);
            userDto.setImageUrl(uploadedImgUrl);
        }

        User user = new User(userDto);

        try {
            userDao.save(user);
        } catch (PersistenceException persistenceException) {
            logger.error("user property is duplicated ! " + persistenceException.getMessage());
            throw new UniquePropertyException("유저의 이메일이나 닉네임이 중복되었습니다");
        }

        return new UserVo(user);
    }
}
