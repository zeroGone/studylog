package io.zerogone.repository;

import ch.qos.logback.classic.Logger;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.User;
import org.slf4j.LoggerFactory;
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
public class UserDao {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public User findUserByEmail(String email) {
        logger.info("-----find user by email : " + email + "-----");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("email"), email));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    public List<User> findAllByBlogId(int blogId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        Join<User, BlogMember> join = root.join("blogs");

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(join.get("blogId"), blogId));

        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    public void save(User user) {
        logger.info("-----save user start-----");

        entityManager.persist(user);
        entityManager.flush();

        logger.debug("created user id : " + user.getId());
        logger.info("-----save blog end-----");
    }
}
