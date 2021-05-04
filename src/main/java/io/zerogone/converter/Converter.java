package io.zerogone.converter;

public interface Converter<K, V> {
    V convert(K key);
}
