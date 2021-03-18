package io.zerogone.issue.repository;

import io.zerogone.issue.model.Issue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class IssueSaveDao {
    private final Log logger = LogFactory.getLog(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public Issue save(Issue issue) {
        logger.info("-----issue save start-----");
        entityManager.persist(issue);
        logger.info("-----issue save end-----");
        return issue;
    }
}
