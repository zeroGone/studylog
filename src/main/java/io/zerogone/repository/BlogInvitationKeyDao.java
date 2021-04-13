package io.zerogone.repository;

import ch.qos.logback.classic.Logger;
import io.zerogone.model.entity.BlogInvitationKey;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BlogInvitationKeyDao {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public void save(BlogInvitationKey blogInvitationKey) {
        logger.info("-----Saving blog invitation key start-----");
        entityManager.persist(blogInvitationKey);
        logger.info("-----Saving blog invitation key is ended-----");
    }
}
