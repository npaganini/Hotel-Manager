package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.SimpleDao;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

abstract class SimpleRepositoryHibernate<T> implements SimpleDao<T> {
    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public T save(T t) {
        em.persist(t);
        em.flush();
        return t;
    }

    @Override
    public Optional<T> findById(long id) {
        return Optional.ofNullable(em.find(getModelClass(), id));
    }

    @Override
    public PaginatedDTO<T> findAll(int page, int pageSize) {
        long count = getTotalCount(getModelClass());
        List<T> tList = em.createQuery("FROM " + getModelName(), getModelClass())
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return new PaginatedDTO<>(tList, count);
    }

    @Override
    public long getTotalCount(Class<T> model) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(model)));
        return em.createQuery(countQuery).getSingleResult();
    }

    @Override
    public long getTotalCount(Class<T> model, Predicate predicate) {
        Predicate[] array = new Predicate[1];
        array[0] = predicate;
        return getTotalCount(model, array);
    }

    public long getTotalCount(Class<T> model, Predicate[] predicates) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cqCount = builder.createQuery(Long.class);
        Root<T> entityRoot = cqCount.from(model);
        cqCount.select(builder.count(entityRoot));
        cqCount.where(builder.and(predicates));

        return em.createQuery(cqCount).getSingleResult();
    }

    abstract String getModelName();

    abstract Class<T> getModelClass();
}
