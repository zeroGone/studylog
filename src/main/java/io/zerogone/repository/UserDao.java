package io.zerogone.repository;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class UserDao {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public void save(User user) {
        logger.info("-----save user start-----");

        entityManager.persist(user);
        entityManager.refresh(user);

        logger.debug("created user id : " + user.getId());
        logger.info("-----save blog end-----");
    }

    public void updateImageUrl(User user) {
        logger.info("-----Updating user's image url start-----");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);

        Root<User> root = criteriaUpdate.from(User.class);
        criteriaUpdate.set(root.get("imageUrl"), user.getImageUrl());
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), user.getId()));

        entityManager.createQuery(criteriaUpdate).executeUpdate();
        logger.info("-----Updating user's image url is ended-----");
    }

    public User findByEmail(String email) {
        logger.info("-----find user by email : " + email + "-----");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("email"), email));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        } catch (NoResultException noResultException) {
            throw new NotExistedDataException(User.class, "이메일로 유저 검색", email);
        }
    }

    public List<User> findAllByBlog(Blog blog) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        Join<User, BlogMember> join = root.join("blogs");

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(join.get("blog"), blog));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
