package io.zerogone.service.create;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface CreateService<T> {
    T create(@Valid T dto);
}
