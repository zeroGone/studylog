package io.zerogone.service.fileupload;

import io.zerogone.model.dto.BlogDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BlogImageUploadService implements ImageUploadService<BlogDto> {
    private static final String UPLOAD_PATH = "img/blog";
    private final AwsUploader awsUploader;

    public BlogImageUploadService(AwsUploader awsUploader) {
        this.awsUploader = awsUploader;
    }

    @Override
    public BlogDto upload(BlogDto dto, MultipartFile image) {
        ImageUrl imageUrl = awsUploader.upload(new Image(UPLOAD_PATH, image));
        dto.setImageUrl(imageUrl.getValue());
        return dto;
    }
}
