package io.zerogone.service.create;

import io.zerogone.validator.NewEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated(NewEntity.class)
public interface CreateService<T> {
    T create(@Valid T dto);
}
