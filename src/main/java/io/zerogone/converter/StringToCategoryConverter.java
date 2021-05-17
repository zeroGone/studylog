package io.zerogone.converter;

import io.zerogone.model.entity.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategoryConverter implements Converter<String, Category> {
    @Override
    public Category convert(String value) {
        return new Category(value);
    }
}
