package io.zerogone.common.fileupload;

import org.springframework.web.multipart.MultipartFile;

public abstract class ImageUploadService {
    private final AwsUploader awsUploader;
    private final String uploadPath;

    public ImageUploadService(AwsUploader awsUploader, String uploadPath) {
        this.awsUploader = awsUploader;
        this.uploadPath = uploadPath;
    }

    public ImageUrl upload(MultipartFile image) {
        if (image == null) {
            return getDefaultImageUrl();
        } else {
            return awsUploader.upload(new Image(uploadPath, image));
        }
    }

    abstract ImageUrl getDefaultImageUrl();
}
