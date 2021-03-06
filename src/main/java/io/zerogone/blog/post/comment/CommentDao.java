package io.zerogone.blog.post.comment;

import io.zerogone.blog.post.comment.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CommentDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Comment comment) {
        entityManager.persist(comment);
    }
}
