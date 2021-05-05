package io.zerogone.repository;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.entity.Blog;
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

@Repository
public class BlogDao {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Blog blog) {
        logger.debug("-----save blog start-----");
        entityManager.persist(blog);
        logger.debug("blog id: " + blog.getId());
        logger.debug("-----save blog end-----");
    }

    public Blog findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Blog> criteriaQuery = criteriaBuilder.createQuery(Blog.class);

        Root<Blog> root = criteriaQuery.from(Blog.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("name"), name));

        TypedQuery<Blog> blogTypedQuery = entityManager.createQuery(criteriaQuery);
        try {
            return blogTypedQuery.getSingleResult();
        } catch (NoResultException noResultException) {
            throw new NotExistedDataException(Blog.class, "블로그 이름으로 블로그 검색", name);
        }
    }

    public Blog findWithBlogMembersByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Blog> criteriaQuery = criteriaBuilder.createQuery(Blog.class);

        Root<Blog> root = criteriaQuery.from(Blog.class);
        Fetch<Blog, BlogMember> memberFetch = root.fetch("members");
        memberFetch.fetch("user");

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("name"), name));

        TypedQuery<Blog> blogTypedQuery = entityManager.createQuery(criteriaQuery);
        try {
            return blogTypedQuery.getSingleResult();
        } catch (NoResultException noResultException) {
            throw new NotExistedDataException(Blog.class, "블로그 이름으로 블로그 검색", name);
        }
    }
}
