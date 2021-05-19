package io.zerogone.common.service;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface SearchService<K, V> {
    V search(@NotNull @Valid K key);
}