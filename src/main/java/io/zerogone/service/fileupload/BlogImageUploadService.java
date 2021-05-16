package io.zerogone.service.fileupload;

import org.springframework.stereotype.Service;

@Service
public class BlogImageUploadService extends ImageUploadService {
    private static final String BLOG_DEFAULT_IMAGE_URL = "/img/blog-default.png";

    public BlogImageUploadService(AwsUploader awsUploader) {
        super(awsUploader, "img/blog");
    }

    @Override
    ImageUrl getDefaultImageUrl() {
        return new ImageUrl(BLOG_DEFAULT_IMAGE_URL);
    }
}
