package io.zerogone.repository;

import ch.qos.logback.classic.Logger;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.BlogMember;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

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

    public Blog findById(int id) {
        return entityManager.find(Blog.class, id);
    }

    public Blog findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Blog> criteriaQuery = criteriaBuilder.createQuery(Blog.class);

        Root<Blog> root = criteriaQuery.from(Blog.class);
        Fetch<Blog, BlogMember> memberFetch = root.fetch("members");
        memberFetch.fetch("user", JoinType.LEFT);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("name"), name));

        TypedQuery<Blog> blogTypedQuery = entityManager.createQuery(criteriaQuery);
        return blogTypedQuery.getSingleResult();
    }

    public Blog findWithBlogMembersByInvitationKey(String invitationKey) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Blog> criteriaQuery = criteriaBuilder.createQuery(Blog.class);

        Root<Blog> root = criteriaQuery.from(Blog.class);
        Fetch<Blog, BlogMember> memberFetch = root.fetch("members");
        memberFetch.fetch("user");
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("invitationKey"), invitationKey));

        TypedQuery<Blog> blogTypedQuery = entityManager.createQuery(criteriaQuery);
        return blogTypedQuery.getSingleResult();
    }
}
