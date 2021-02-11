package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.dtos.PaginatedDTO;

import javax.persistence.criteria.Predicate;
import java.util.Optional;

public interface SimpleDao<T> {
    T save(T t);

    Optional<T> findById(long id);

    PaginatedDTO<T> findAll(int page, int pageSize);

    long getTotalCount(Class<T> model);

    long getTotalCount(Class<T> model, Predicate predicate);

    long getTotalCount(Class<T> model, Predicate[] predicates);
}
