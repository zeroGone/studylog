package io.zerogone.blog.post;

import io.zerogone.blog.model.Blog;
import io.zerogone.blog.post.model.Post;
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
public class PostDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Post post) {
        entityManager.persist(post);
    }

    public Post findById(int id) {
        return entityManager.find(Post.class, id);
    }

    public List<Post> findAllByBlogName(String blogName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);

        Root<Post> root = criteriaQuery.from(Post.class);
        Join<Post, Blog> blogJoin = root.join("blog");

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(blogJoin.get("name"), blogName));

        TypedQuery<Post> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
