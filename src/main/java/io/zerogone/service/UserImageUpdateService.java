package io.zerogone.service;

import io.zerogone.model.UserDto;
import io.zerogone.model.UserVo;
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

        User entity = convert(user, savedImgUrl);
        userDao.updateImageUrl(entity);

        return new UserVo(entity);
    }

    private User convert(UserVo vo, String imageUrl) {
        UserDto dto = new UserDto();
        dto.setId(vo.getId());
        dto.setName(vo.getName());
        dto.setEmail(vo.getEmail());
        dto.setNickName(vo.getNickName());
        dto.setImageUrl(imageUrl);
        return new User(dto);
    }
}
