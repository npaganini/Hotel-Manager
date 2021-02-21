package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.webapp.dtos.CredentialsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Priority;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Priorities;
import java.io.IOException;
import java.util.ArrayList;

@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;
    private final TokenAuthHandlerService tokenAuthHandlerService;

    @Autowired
    public AuthenticationFilter(AuthenticationManager authenticationManager, TokenAuthHandlerService tokenAuthHandlerService) {
        this.authenticationManager = authenticationManager;
        this.tokenAuthHandlerService = tokenAuthHandlerService;
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            CredentialsDTO credentials = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class);
            LOGGER.debug("Attempting authentication...");
            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    credentials.getUsername(), credentials.getPassword(), new ArrayList<>()));
        } catch (IOException exception) {
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        LOGGER.debug("Authentication successful!");
        tokenAuthHandlerService.addAuthToResponse(response, auth.getName());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
