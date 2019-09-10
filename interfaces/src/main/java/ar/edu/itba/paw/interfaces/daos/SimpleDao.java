package ar.edu.itba.paw.interfaces.daos;

import java.util.Optional;

public interface SimpleDao<T> {
    T save(T t);
    Optional<T> findById(long id);
}
