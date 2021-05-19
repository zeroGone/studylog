package io.zerogone.blog.post.converter;

import io.zerogone.blog.post.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToStringConverter implements Converter<Category, String> {
    @Override
    public String convert(Category category) {
        return category.getName();
    }
}
