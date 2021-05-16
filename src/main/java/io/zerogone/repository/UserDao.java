package io.zerogone.repository;

import ch.qos.logback.classic.Logger;
import io.zerogone.model.entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

@Repository
public class UserDao {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public void save(User user) {
        logger.info("-----save user start-----");
        entityManager.persist(user);
        logger.debug("created user id : " + user.getId());
        logger.info("-----save user is end-----");
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

    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    public User findByEmail(String email) {
        logger.info("-----Find user with user's blogs by email : " + email + " -----");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        root.fetch("blogs", JoinType.LEFT);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("email"), email));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    public User findByNickName(String nickName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("nickName"), nickName));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
