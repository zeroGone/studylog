package io.zerogone.service.create;

import io.zerogone.model.dto.DataTransferObject;
import org.springframework.web.multipart.MultipartFile;

public interface CreateWithImageService {
    DataTransferObject create(DataTransferObject dto, MultipartFile image);

    DataTransferObject create(DataTransferObject dto);
}
