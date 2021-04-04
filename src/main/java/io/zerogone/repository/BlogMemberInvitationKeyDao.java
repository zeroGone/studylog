package io.zerogone.repository;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.entity.BlogMemberInvitationKey;
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

    public BlogMemberInvitationKey findBlogMemberInvitationKeyByValue(String value) {
        logger.debug("-----find key by value : " + value);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BlogMemberInvitationKey> criteriaQuery = criteriaBuilder.createQuery(BlogMemberInvitationKey.class);

        Root<BlogMemberInvitationKey> root = criteriaQuery.from(BlogMemberInvitationKey.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("value"), value));

        TypedQuery<BlogMemberInvitationKey> query = entityManager.createQuery(criteriaQuery);

        try {
            return query.getSingleResult();
        } catch (NoResultException noResultException) {
            throw new NotExistedDataException(BlogMemberInvitationKey.class, "키 값으로 멤버 초대 데이터 검색", value);
        }
    }
}
