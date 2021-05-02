package io.zerogone.repository;

import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.entity.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class CategoryDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Category entity) {
        entityManager.persist(entity);
    }

    public Category findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);

        Root<Category> root = criteriaQuery.from(Category.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("name"), name));

        TypedQuery<Category> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        } catch (NoResultException noResultException) {
            throw new NotExistedDataException(Category.class, "카테고리 검색", name);
        }
    }
}
