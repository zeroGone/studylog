package io.zerogone.service.create;

import io.zerogone.model.dto.DataTransferObject;
import io.zerogone.service.fileupload.ImageUploadService;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

public abstract class CreateWithImageService {
    private final ImageUploadService imageUploadService;

    public CreateWithImageService(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }

    @Transactional
    public DataTransferObject create(DataTransferObject dto, MultipartFile image) {
        return create(imageUploadService.upload(dto, image));
    }

    @Transactional
    public abstract DataTransferObject create(DataTransferObject dto);
}
