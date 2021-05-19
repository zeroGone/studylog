package io.zerogone.blog.post.converter;

import io.zerogone.blog.post.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategoryConverter implements Converter<String, Category> {
    @Override
    public Category convert(String value) {
        return new Category(value);
    }
}
