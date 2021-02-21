package ar.edu.itba.paw.webapp.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Priority;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Priorities;
import java.io.IOException;
import java.util.Optional;

@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter extends BasicAuthenticationFilter {
    private static final String AUTH_HEADER = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Bearer ";
    private static final int SECS_PER_MIN = 60;
    private static final int MINUTES = 10;
    private static final int MAX_AGE = SECS_PER_MIN * MINUTES;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);

    private final CustomUserDetailsService userDetailsService;
    private final TokenAuthHandlerService tokenAuthHandlerService;

    public AuthorizationFilter(AuthenticationManager authenticationManager,
                               TokenAuthHandlerService tokenService,
                               CustomUserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.tokenAuthHandlerService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
        final String header = httpServletRequest.getHeader(AUTH_HEADER);
        LOGGER.info("Applying filters to request...");
        if (header == null || !header.startsWith(AUTHENTICATION_SCHEME)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            LOGGER.info("No authorization sent on request");
            return;
        }
        LOGGER.info("Resource requested: " + httpServletRequest.getPathInfo());
        UsernamePasswordAuthenticationToken authentication = getAuthentication(httpServletRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // the Cache-Control: max-age directive tells the cache how many seconds the response is fresh for
        httpServletResponse.setHeader("Cache-Control", "public, max-age=" + MAX_AGE);
        httpServletResponse.setHeader("Connection", "Keep-Alive");
        httpServletResponse.setHeader("Keep-Alive", "timeout=5, max=25");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(AUTH_HEADER);
        if (token != null) {
            Optional<String> possibleUser = tokenAuthHandlerService.validateToken(token);
            if (possibleUser.isPresent()) {
                String user = possibleUser.get();
                LOGGER.info("User found that uses that token! User is: " + user);
                MyUserPrincipal principal = (MyUserPrincipal) userDetailsService.loadUserByUsername(user);
                return new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
            }
            LOGGER.info("No user found for that token!");
            return null;
        }
        LOGGER.info("No bearer token found in request!");
        return null;
    }
}
