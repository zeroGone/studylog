package io.zerogone.user.repository;

import com.querydsl.jpa.impl.JPAQuery;
import io.zerogone.user.model.LoginRequest;
import io.zerogone.user.model.QUser;
import io.zerogone.user.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public class FindUserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<User> findByLoginRequest(@NotNull @Valid LoginRequest loginRequest) {
        JPAQuery<User> query = new JPAQuery<>(entityManager);
        QUser user = QUser.user;
        return Optional.ofNullable(query
                .from(user)
                .where(user.email.eq(loginRequest.getEmail()), user.name.eq(loginRequest.getName()))
                .fetchOne());
    }
}
