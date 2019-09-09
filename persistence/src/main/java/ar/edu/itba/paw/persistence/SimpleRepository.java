package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.SimpleDao;

import java.util.Optional;

public class SimpleRepository<T> implements SimpleDao<T> {
    @Override
    public T save(T t) {
        return null;
    }

    @Override
    public int update(T t) {
        return 0;
    }

    @Override
    public Optional<T> findById(long id) {
        return Optional.empty();
    }
}
