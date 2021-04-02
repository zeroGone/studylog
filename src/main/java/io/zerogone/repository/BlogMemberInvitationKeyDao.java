package io.zerogone.repository;

import ch.qos.logback.classic.Logger;
import io.zerogone.model.entity.BlogMemberInvitationKey;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BlogMemberInvitationKeyDao {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public void save(List<BlogMemberInvitationKey> blogMemberInvitationKeys) {
        logger.debug("-----Saving blog member's invitation keys start-----");

        for (BlogMemberInvitationKey key : blogMemberInvitationKeys) {
            entityManager.persist(key);
        }
        entityManager.flush();

        logger.debug("-----Saving blog member's invitation keys is ended-----");
    }
}
