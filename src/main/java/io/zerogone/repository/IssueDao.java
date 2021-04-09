package io.zerogone.repository;

import ch.qos.logback.classic.Logger;
import io.zerogone.model.entity.Issue;
import io.zerogone.model.entity.IssueCategory;
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
public class IssueDao {

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public List<Issue> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);

        Root<Issue> root = criteriaQuery.from(Issue.class);

        criteriaQuery.select(root);

        TypedQuery<Issue> issueTypedQuery = entityManager.createQuery(criteriaQuery);
        return issueTypedQuery.getResultList();
    }


}
