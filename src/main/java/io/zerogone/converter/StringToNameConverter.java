package io.zerogone.converter;

import io.zerogone.model.Name;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToNameConverter implements Converter<String, Name> {
    @Override
    public Name convert(String value) {
        return new Name(value);
    }
}
