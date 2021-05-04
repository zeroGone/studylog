package io.zerogone.repository;

import ch.qos.logback.classic.Logger;
import io.zerogone.model.entity.BlogMember;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BlogMemberDao {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public void save(BlogMember blogMember) {
        logger.info("-----Saving blog member start-----");

        entityManager.persist(blogMember);

        logger.info("-----Saving blog member is ended-----");
    }

    public void save(List<BlogMember> blogMembers) {
        logger.info("-----Saving blog members start-----");

        for (BlogMember blogMember : blogMembers) {
            entityManager.persist(blogMember);
        }

        logger.info("-----Saving blog member is ended-----");
    }
}
