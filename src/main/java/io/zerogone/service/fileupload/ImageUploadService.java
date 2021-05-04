package io.zerogone.service.fileupload;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService<T> {
    T upload(T dto, MultipartFile image);
}
