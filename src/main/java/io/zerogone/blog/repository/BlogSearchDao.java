package io.zerogone.blog.repository;

import io.zerogone.blog.model.Blog;
import io.zerogone.blog.model.BlogMember;
import io.zerogone.blog.model.MemberRole;
import io.zerogone.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BlogSearchDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Blog> findAllByUser(User user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Blog> criteriaQuery = criteriaBuilder.createQuery(Blog.class);

        Root<Blog> root = criteriaQuery.from(Blog.class);
        Join<Blog, BlogMember> join = root.join("members");

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.and(
                criteriaBuilder.equal(join.get("userId"), user.getId()),
                criteriaBuilder.or(
                        criteriaBuilder.equal(join.get("role"), MemberRole.MEMBER),
                        criteriaBuilder.equal(join.get("role"), MemberRole.ADMIN))));

        TypedQuery<Blog> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}
