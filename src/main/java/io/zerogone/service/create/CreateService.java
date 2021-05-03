package io.zerogone.service.create;

import io.zerogone.model.dto.DataTransferObject;
import io.zerogone.service.fileupload.ImageUploadService;
import org.springframework.web.multipart.MultipartFile;

public abstract class CreateService {
    private final ImageUploadService imageUploadService;

    public CreateService(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }

    public DataTransferObject create(DataTransferObject dto, MultipartFile image) {
        return create(imageUploadService.upload(dto, image));
    }

    public abstract DataTransferObject create(DataTransferObject dto);
}
