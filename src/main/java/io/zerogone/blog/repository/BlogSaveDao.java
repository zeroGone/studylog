package io.zerogone.blog.repository;

import io.zerogone.blog.exception.InvalidBlogMemberException;
import io.zerogone.blog.model.Blog;
import io.zerogone.blog.model.BlogMember;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class BlogSaveDao {
    private final Log logger = LogFactory.getLog(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public Blog save(Blog blog, List<BlogMember> blogMembers) throws InvalidBlogMemberException {
        logger.debug("-----save blog start-----");

        entityManager.persist(blog);
        entityManager.flush();

        logger.debug("blog id: " + blog.getId());

        for (BlogMember blogMember : blogMembers) {
            blogMember.setBlogId(blog.getId());
            try {
                entityManager.persist(blogMember);
            } catch (PersistenceException persistenceException) {
                throw new InvalidBlogMemberException();
            }
        }
        entityManager.flush();
        entityManager.close();
        logger.debug("-----save blog end-----");

        return blog;
    }
}
