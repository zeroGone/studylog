package io.zerogone.user.service;

import io.zerogone.common.service.CreateWithImageService;
import io.zerogone.user.model.UserDto;
import io.zerogone.user.model.User;
import io.zerogone.user.UserDao;
import io.zerogone.common.fileupload.ImageUploadService;
import io.zerogone.common.fileupload.ImageUrl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class UserCreateService implements CreateWithImageService<UserDto> {
    private final UserDao userDao;
    private final ConversionService conversionService;
    private final ImageUploadService imageUploadService;

    public UserCreateService(UserDao userDao,
                             ConversionService conversionService,
                             @Qualifier("userImageUploadService") ImageUploadService imageUploadService) {
        this.userDao = userDao;
        this.conversionService = conversionService;
        this.imageUploadService = imageUploadService;
    }

    @Transactional
    @Override
    public UserDto create(UserDto dto, MultipartFile image) {
        ImageUrl imageUrl = imageUploadService.upload(image);
        dto.setImageUrl(imageUrl.getValue());
        return create(dto);
    }

    @Transactional
    @Override
    public UserDto create(UserDto dto) {
        User entity = conversionService.convert(dto, User.class);
        userDao.save(entity);
        return conversionService.convert(entity, UserDto.class);
    }
}
