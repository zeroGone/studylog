package io.zerogone.blog.repository;

import io.zerogone.blog.model.Blog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BlogDao {
    private final Log logger = LogFactory.getLog(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Blog blog) {
        logger.debug("-----save blog start-----");

        entityManager.persist(blog);
        entityManager.flush();

        logger.debug("blog id: " + blog.getId());
        logger.debug("-----save blog end-----");
    }
}
