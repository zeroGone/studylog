package io.zerogone.service.search;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface SearchService<K, V> {
    V search(@NotNull @Valid K key);
}