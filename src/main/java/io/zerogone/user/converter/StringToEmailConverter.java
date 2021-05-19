package io.zerogone.user.converter;

import io.zerogone.user.model.Email;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEmailConverter implements Converter<String, Email> {
    @Override
    public Email convert(String value) {
        return new Email(value);
    }
}
