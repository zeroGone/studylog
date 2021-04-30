package io.zerogone.service.create;

import io.zerogone.model.dto.CreateDto;
import io.zerogone.model.vo.ValueObject;

public interface CreateService {
    ValueObject createEntity(CreateDto dto);
}
