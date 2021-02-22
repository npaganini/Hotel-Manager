package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.UnsupportedAuthorizationMethodException;
import ar.edu.itba.paw.models.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Component
@PropertySource("classpath:key.properties")
public class TokenAuthHandlerService {
    private static final String AUTH_HEADER = "Authorization";
    private static final int DAYS_UNTIL_TOKEN_EXPIRES = 60;
    private static final String AUTHENTICATION_SCHEME = "Bearer ";

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthHandlerService.class);

    private final UserDao userDao;
    @Value(value = "${key}")
    private String key;

    @Autowired
    public TokenAuthHandlerService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<Long> getUserIdFromAuth(final HttpServletRequest request) throws EntityNotFoundException, UnsupportedAuthorizationMethodException {
        final String token = request.getHeader(AUTH_HEADER);
        if (token == null) {
            throw new UnsupportedAuthorizationMethodException();
        }

        final String username = getUsername(token);
        Optional<User> possibleUser = userDao.findByUsername(username);
        return possibleUser.map(User::getId);
    }

    private String createToken(final String username) {
        LOGGER.info("Creating new token for user: " + username);
        final ZonedDateTime now = ZonedDateTime.now();
        final Date expirationDate = Date.from(now.plusDays(DAYS_UNTIL_TOKEN_EXPIRES).toInstant());
        String token = Jwts.builder()
                .setIssuer("Hotel Manager")
                .setSubject(username)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        System.out.println("Token created!");
        return token;
    }

    public String getUsername(final String token) {
        LOGGER.info("Retrieving username for token: " + token);
        return getAllClaimsFromToken(token).getSubject();
    }

    private Claims getAllClaimsFromToken(String token) {
        LOGGER.info("Retrieving all claims for token: " + token);
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token.replace(AUTHENTICATION_SCHEME, ""))
                .getBody();
    }

    private boolean isTokenExpired(Claims tokenClaims) {
        final Date expiration = tokenClaims.getExpiration();
        return expiration.before(new Date());
    }

    public Optional<String> validateToken(String token) {
        LOGGER.info("Validating token...");
        Claims tokenClaims = getAllClaimsFromToken(token);
        if (tokenClaims != null) {
            if (!isTokenExpired(tokenClaims)) {
                return Optional.of(tokenClaims.getSubject());
            }
            LOGGER.info("Token expired!");
        }
        return Optional.empty();
    }

    public void addAuthToResponse(HttpServletResponse response, String username) throws IOException {
        // If we are at this point, there is no chance user is no longer available, it just crash everything if this happens
        User user = userDao.findByUsername(username).get();

        final LoginResponse token = new LoginResponse(createToken(username), user.getRole().toString());
        final String tokenResponseJson = new ObjectMapper().writeValueAsString(token);
        response.getWriter().write(tokenResponseJson);
        response.flushBuffer();
    }
}
