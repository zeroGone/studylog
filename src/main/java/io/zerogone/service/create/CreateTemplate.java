package io.zerogone.service.create;

import io.zerogone.converter.Converter;
import io.zerogone.model.dto.DataTransferObject;

import javax.transaction.Transactional;

public abstract class CreateTemplate<T> {
    private final Converter<T> converter;

    public CreateTemplate(Converter<T> converter) {
        this.converter = converter;
    }

    @Transactional
    public DataTransferObject create(DataTransferObject dto) {
        validate(dto);
        T entity = converter.convert(dto);
        entity = saveEntity(entity);
        return converter.convert(entity);
    }

    abstract T saveEntity(T entity);

    abstract void validate(DataTransferObject dto);
}
