package ar.edu.itba.paw.interfaces.daos;

import java.util.List;
import java.util.Optional;

public interface SimpleDao<T> {
    T save(T t);
    Optional<T> findById(long id);
    List<T> findAll();
}
