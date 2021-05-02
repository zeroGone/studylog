package io.zerogone.service.fileupload;

import io.zerogone.model.dto.DataTransferObject;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
    DataTransferObject upload(DataTransferObject dto, MultipartFile image);
}
