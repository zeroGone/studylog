package io.zerogone.model.dto;

public interface CreateDto<T> {
    T convertToEntity();
}
