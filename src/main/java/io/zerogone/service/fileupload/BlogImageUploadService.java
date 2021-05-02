package io.zerogone.service.fileupload;

import io.zerogone.model.dto.DataTransferObject;
import io.zerogone.model.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

public class BlogImageUploadService implements ImageUploadService {
    private static final String UPLOAD_PATH = "img/blog";
    private final AwsUploader awsUploader;

    public BlogImageUploadService(AwsUploader awsUploader) {
        this.awsUploader = awsUploader;
    }

    @Override
    public DataTransferObject upload(DataTransferObject dto, MultipartFile image) {
        ImageUrl imageUrl = awsUploader.upload(new Image(UPLOAD_PATH, image));
        UserDto userDto = (UserDto) dto;
        userDto.setImageUrl(imageUrl.getValue());
        return userDto;
    }
}
