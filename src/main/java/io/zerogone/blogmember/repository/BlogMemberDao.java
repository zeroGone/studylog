package io.zerogone.blogmember.repository;

import io.zerogone.blogmember.exception.BlogMembersStateException;
import io.zerogone.blogmember.model.BlogMember;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class BlogMemberDao {
    private final Log logger = LogFactory.getLog(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public void save(List<BlogMember> blogMembers) {
        logger.debug("-----save blogmember start-----");

        for (BlogMember blogMember : blogMembers) {
            try {
                entityManager.persist(blogMember);
            } catch (PersistenceException persistenceException) {
                throw new BlogMembersStateException("It isn't allowed blog members to include invalid blog id or user id");
            }
        }

        entityManager.flush();

        logger.debug("-----save blogmember end-----");
    }
}
