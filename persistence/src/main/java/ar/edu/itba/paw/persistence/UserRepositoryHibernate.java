package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.models.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryHibernate implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<User> findByEmail(String userEmail) {
        final TypedQuery<User> query = em.createQuery("from User as u where u.email = :userEmail", User.class);
        query.setParameter("userEmail", userEmail);
        final List<User> list = query.getResultList();
        return Optional.ofNullable(list.get(0));    // returns null if list is empty (no user with that username found)
    }

    @Override
    public Optional<User> findByUsername(String username) {
        final TypedQuery<User> query = em.createQuery("from User as u where u.username = :username", User.class);
        query.setParameter("username", username);
        final List<User> list = query.getResultList();
        return Optional.ofNullable(list.get(0));    // returns null if list is empty (no user with that username found)
    }

    @Override
    public User save(User user) {
        final User userToAdd = new User(user.getEmail(), user.getUsername(), user.getPassword());
        em.persist(userToAdd);
        return userToAdd;
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("from User", User.class).getResultList();
    }
}
