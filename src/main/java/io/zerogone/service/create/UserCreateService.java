package io.zerogone.service.create;

import io.zerogone.model.dto.DataTransferObject;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.User;
import io.zerogone.service.fileupload.ImageUploadService;
import org.springframework.stereotype.Service;

@Service
public class UserCreateService extends CreateService {
    private static final String USER_DEFAULT_IMAGE_URL = "/img/user-default/";
    private static final String USER_DEFAULT_IMAGE_TYPE = ".png";
    private final CreateTemplate<User> createTemplate;

    public UserCreateService(ImageUploadService imageUploadService, CreateTemplate<User> createTemplate) {
        super(imageUploadService);
        this.createTemplate = createTemplate;
    }

    @Override
    public DataTransferObject create(DataTransferObject dto) {
        UserDto userDto = (UserDto) dto;

        if (userDto.getImageUrl() == null) {
            int randomNumber = (int) (Math.random() * 10);
            userDto.setImageUrl(USER_DEFAULT_IMAGE_URL + randomNumber + USER_DEFAULT_IMAGE_TYPE);
        }

        return createTemplate.create(dto);
    }
}
