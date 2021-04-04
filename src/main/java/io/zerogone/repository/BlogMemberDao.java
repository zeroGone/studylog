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

    public void save(List<BlogMember> blogMembers) {
        logger.debug("-----save blogmember start-----");

        for (BlogMember blogMember : blogMembers) {
            entityManager.persist(blogMember);
        }
        entityManager.flush();

        logger.debug("-----save blogmember end-----");
    }

    public void update(BlogMember blogMember) {
        logger.info("-----Updating blog member's role Inviting -> Member start-----");
        entityManager.merge(blogMember);
        logger.info("-----Updating blog member's role Inviting -> Member is ended-----");
    }
}
