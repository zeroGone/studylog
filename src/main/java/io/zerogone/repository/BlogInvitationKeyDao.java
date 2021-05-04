package io.zerogone.repository;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.entity.BlogInvitationKey;
import io.zerogone.model.entity.BlogMember;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BlogInvitationKeyDao {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public void save(List<BlogInvitationKey> blogInvitationKeys) {
        logger.info("-----Saving blog invitation key start-----");
        blogInvitationKeys.forEach(blogInvitationKey -> entityManager.persist(blogInvitationKey));
        logger.info("-----Saving blog invitation key is ended-----");
    }

    public BlogInvitationKey findWithBlogMemberByValue(String value) {
        logger.info("-----Find blog member by blog invitation key value-----");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BlogInvitationKey> criteriaQuery = criteriaBuilder.createQuery(BlogInvitationKey.class);

        Root<BlogInvitationKey> root = criteriaQuery.from(BlogInvitationKey.class);
        Fetch<BlogInvitationKey, BlogMember> blogMemberFetch = root.fetch("owner");
        blogMemberFetch.fetch("blog");

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("value"), value));

        TypedQuery<BlogInvitationKey> blogTypedQuery = entityManager.createQuery(criteriaQuery);
        try {
            return blogTypedQuery.getSingleResult();
        } catch (NoResultException noResultException) {
            throw new NotExistedDataException(BlogInvitationKey.class, "초대 키 값으로 블래그 초대 키 검색", value);
        }
    }
}
