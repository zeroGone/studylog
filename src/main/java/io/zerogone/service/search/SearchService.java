package io.zerogone.service.search;

public interface SearchService<K, V> {
    V search(K key);
}
