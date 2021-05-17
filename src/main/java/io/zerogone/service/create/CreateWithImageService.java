package io.zerogone.service.create;

import io.zerogone.validator.NewEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated(NewEntity.class)
public interface CreateWithImageService<T> extends CreateService<T> {
    T create(@Valid T dto, @NotNull MultipartFile image);
}
