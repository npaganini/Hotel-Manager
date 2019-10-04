package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.user.User;

import java.time.ZoneId;

public interface UserDao extends SimpleDao<User>{
    User findByEmail(String userEmail);
    User findByUsername(String username);
}
