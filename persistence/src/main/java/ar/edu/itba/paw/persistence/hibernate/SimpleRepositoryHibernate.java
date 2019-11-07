package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.SimpleDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

abstract class SimpleRepositoryHibernate<T> implements SimpleDao<T> {

    @PersistenceContext
    EntityManager em;

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
    public List<T> findAll() {
        return em.createQuery("FROM " + getTableName(), getModelClass()).getResultList();
    }

    abstract String getTableName();

    abstract Class<T> getModelClass();

}
