package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.user.User;

import java.util.Optional;

public interface UserDao extends SimpleDao<User>{
    Optional<User> findByEmail(String userEmail);
    Optional<User> findByUsername(String username);
}
