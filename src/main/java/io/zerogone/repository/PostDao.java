package io.zerogone.repository;

import io.zerogone.model.entity.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PostDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Post post) {
        entityManager.persist(post);
    }

    public Post findById(int id) {
        return entityManager.find(Post.class, id);
    }
}
