package io.zerogone.repository;

import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.MemberRole;
import io.zerogone.exception.UniquePropertyException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BlogDao {
    private final Log logger = LogFactory.getLog(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Blog blog) {
        logger.debug("-----save blog start-----");

        try {
            entityManager.persist(blog);
        } catch (PersistenceException persistenceException) {
            throw new UniquePropertyException("Blog Name is null or duplicated");
        }
        entityManager.flush();

        logger.debug("blog id: " + blog.getId());
        logger.debug("-----save blog end-----");
    }

    public List<Blog> findAllByUserId(int userId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Blog> criteriaQuery = criteriaBuilder.createQuery(Blog.class);

        Root<Blog> root = criteriaQuery.from(Blog.class);
        Join<Blog, BlogMember> join = root.join("members");

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.and(
                criteriaBuilder.equal(join.get("userId"), userId),
                criteriaBuilder.or(
                        criteriaBuilder.equal(join.get("role"), MemberRole.MEMBER),
                        criteriaBuilder.equal(join.get("role"), MemberRole.ADMIN))));

        TypedQuery<Blog> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    public Blog findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Blog> criteriaQuery = criteriaBuilder.createQuery(Blog.class);

        Root<Blog> root = criteriaQuery.from(Blog.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("name"), name));

        TypedQuery<Blog> blogTypedQuery = entityManager.createQuery(criteriaQuery);
        return blogTypedQuery.getSingleResult();
    }
}
