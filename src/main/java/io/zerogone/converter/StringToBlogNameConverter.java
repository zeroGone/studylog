package io.zerogone.converter;

import io.zerogone.model.BlogName;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToBlogNameConverter implements Converter<String, BlogName> {
    @Override
    public BlogName convert(String value) {
        return new BlogName(value);
    }
}
