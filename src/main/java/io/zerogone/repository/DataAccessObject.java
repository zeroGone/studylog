package io.zerogone.repository;

public interface DataAccessObject<T> {
    void save(T entity);
}
