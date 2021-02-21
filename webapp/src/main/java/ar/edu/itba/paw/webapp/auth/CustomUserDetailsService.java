package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.models.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UserDao userDao;

    @Autowired
    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Looking for username in database...");
        User user = userDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new MyUserPrincipal(user);
    }
}
