package io.zerogone.service.create;

import io.zerogone.model.dto.CreateDto;
import io.zerogone.model.dto.PostCreateDto;
import io.zerogone.model.entity.Post;
import io.zerogone.model.vo.PostVo;
import io.zerogone.model.vo.ValueObject;
import io.zerogone.repository.DataAccessObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostCreateService implements CreateService {
    private final DataAccessObject<Post> dataAccessObject;

    public PostCreateService(DataAccessObject<Post> dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
    }

    @Transactional
    @Override
    public ValueObject createEntity(CreateDto dto) {
        PostCreateDto postCreateDto = (PostCreateDto) dto;
        Post entity = postCreateDto.convertToEntity();
        dataAccessObject.save(entity);
        return new PostVo(entity);
    }
}
