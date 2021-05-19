package io.zerogone.common.service;

import io.zerogone.common.NewEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated(NewEntity.class)
public interface CreateService<T> {
    T create(@Valid T dto);
}
