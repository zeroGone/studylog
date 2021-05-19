package io.zerogone.user.service;

import io.zerogone.common.fileupload.AwsUploader;
import io.zerogone.common.fileupload.ImageUploadService;
import io.zerogone.common.fileupload.ImageUrl;
import org.springframework.stereotype.Service;

@Service
public class UserImageUploadService extends ImageUploadService {
    private static final String USER_DEFAULT_IMAGE_URL = "/img/user-default/";
    private static final String USER_DEFAULT_IMAGE_TYPE = ".png";

    public UserImageUploadService(AwsUploader awsUploader) {
        super(awsUploader, "img/user");
    }

    @Override
    protected ImageUrl getDefaultImageUrl() {
        int randomNumber = (int) (Math.random() * 10);
        return new ImageUrl(USER_DEFAULT_IMAGE_URL + randomNumber + USER_DEFAULT_IMAGE_TYPE);
    }
}
