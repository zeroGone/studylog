package io.zerogone.service.fileupload;

import io.zerogone.model.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserImageUploadService implements ImageUploadService<UserDto> {
    private static final String UPLOAD_PATH = "img/user";
    private final AwsUploader awsUploader;

    public UserImageUploadService(AwsUploader awsUploader) {
        this.awsUploader = awsUploader;
    }

    @Override
    public UserDto upload(UserDto dto, MultipartFile image) {
        ImageUrl imageUrl = awsUploader.upload(new Image(UPLOAD_PATH, image));
        dto.setImageUrl(imageUrl.getValue());
        return dto;
    }
}
