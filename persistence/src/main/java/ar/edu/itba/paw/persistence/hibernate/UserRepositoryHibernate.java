package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.models.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryHibernate extends SimpleRepositoryHibernate<User> implements UserDao {

    @Override
    public Optional<User> findByEmail(String userEmail) {
        final TypedQuery<User> query = em.createQuery("SELECT u FROM User as u where u.email = :userEmail", User.class);
        query.setParameter("userEmail", userEmail);
        final List<User> list = query.getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));    // returns null if list is empty (no user with that username found)
    }

    @Override
    public Optional<User> findByUsername(String username) {
        final TypedQuery<User> query = em.createQuery("SELECT u FROM User as u where u.username = :username", User.class);
        query.setParameter("username", username);
        final List<User> list = query.getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));    // returns null if list is empty (no user with that username found)
    }

    @Override
    String getModelName() {
        return User.NAME + " ";
    }

    @Override
    Class<User> getModelClass() {
        return User.class;
    }
}
