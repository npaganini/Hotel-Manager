package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MyUserPrincipal implements UserDetails {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserPrincipal.class);

    private User user;
    private List<GrantedAuthority> authorities;

    public MyUserPrincipal(User user) {
        LOGGER.debug("Username found!");
        LOGGER.debug("Username userDetails: " + user.getUsername());
        this.user = user;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
