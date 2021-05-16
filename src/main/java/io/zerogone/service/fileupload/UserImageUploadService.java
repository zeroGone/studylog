package io.zerogone.service.fileupload;

import org.springframework.stereotype.Service;

@Service
public class UserImageUploadService extends ImageUploadService {
    private static final String USER_DEFAULT_IMAGE_URL = "/img/user-default/";
    private static final String USER_DEFAULT_IMAGE_TYPE = ".png";

    public UserImageUploadService(AwsUploader awsUploader) {
        super(awsUploader, "img/user");
    }

    @Override
    ImageUrl getDefaultImageUrl() {
        int randomNumber = (int) (Math.random() * 10);
        return new ImageUrl(USER_DEFAULT_IMAGE_URL + randomNumber + USER_DEFAULT_IMAGE_TYPE);
    }
}
