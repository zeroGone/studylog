package io.zerogone.repository;

import com.querydsl.jpa.impl.JPAQuery;
import io.zerogone.domain.entity.QUser;
import io.zerogone.domain.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    public Optional<User> findByEmail(String email) {
        System.out.println(entityManager.find(User.class, 1L));
        JPAQuery<User> query = new JPAQuery<>(entityManager);
        QUser user = QUser.user;
        return Optional.ofNullable(query
                .from(user)
                .where(user.email.eq(email))
                .fetchOne());
    }
}
