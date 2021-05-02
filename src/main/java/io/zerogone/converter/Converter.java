package io.zerogone.converter;

import io.zerogone.model.dto.DataTransferObject;

public interface Converter<T> {
    T convert(DataTransferObject dto);

    DataTransferObject convert(T entity);
}
