package io.zerogone.repository;

import ch.qos.logback.classic.Logger;
import io.zerogone.model.entity.Category;
import io.zerogone.model.entity.Post;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Repository
public class PostDao implements DataAccessObject<Post> {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Post post) {
        logger.info("-----Save Post start-----");

        for (Category category : post.getCategories()) {
            try {
                entityManager.persist(category);
            } catch (PersistenceException persistenceException) {
            }
        }
        post.getCategories().forEach(category -> entityManager.persist(category));
        entityManager.persist(post);
        logger.info("-----Save Post is ended-----");
    }
}
