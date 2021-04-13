package io.zerogone.repository;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.entity.BlogMember;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BlogMemberDao {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public void save(BlogMember blogMember) {
        logger.info("-----Saving blog member start-----");

        entityManager.persist(blogMember);
        entityManager.flush();

        logger.info("-----Saving blog member is ended-----");
    }

    public void save(List<BlogMember> blogMembers) {
        logger.info("-----Saving blog members start-----");

        for (BlogMember blogMember : blogMembers) {
            entityManager.persist(blogMember);
        }
        entityManager.flush();

        logger.info("-----Saving blog member is ended-----");
    }

    public void update(BlogMember blogMember) {
        logger.info("-----Updating blog member start-----");
        entityManager.merge(blogMember);
        logger.info("-----Updating blog member is ended-----");
    }

    public BlogMember findByBlogInviationKeyValue(String blogInvitationKeyValue) {
        logger.info("-----Find blog member by blog invitation key value-----");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BlogMember> criteriaQuery = criteriaBuilder.createQuery(BlogMember.class);

        Root<BlogMember> root = criteriaQuery.from(BlogMember.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("blogInvitationKey").get("value"), blogInvitationKeyValue));

        TypedQuery<BlogMember> blogTypedQuery = entityManager.createQuery(criteriaQuery);
        blogTypedQuery.setHint("javax.persistence.loadgraph", entityManager.getEntityGraph("blog-member-by-blog-invitation-key-with-blog"));
        try {
            return blogTypedQuery.getSingleResult();
        } catch (NoResultException noResultException) {
            throw new NotExistedDataException(BlogMember.class, "블로그 초대 키로 멤버 검색", blogInvitationKeyValue);
        }
    }
}
