package io.zerogone.blog.repository;

import io.zerogone.blog.model.Blog;
import io.zerogone.blog.model.BlogMember;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BlogSaveRepository {
    private final Log logger = LogFactory.getLog(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public Blog save(Blog blog, List<BlogMember> blogMembers) {
        logger.debug("-----save blog start-----");

        entityManager.persist(blog);
        entityManager.flush();

        logger.debug("blog : " + blog);

        for (BlogMember blogMember : blogMembers) {
            blogMember.setBlogId(blog.getId());
            entityManager.persist(blogMember);
        }
        entityManager.flush();
        entityManager.close();
        logger.debug("-----save blog end-----");

        return blog;
    }
}
