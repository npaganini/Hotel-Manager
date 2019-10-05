package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.webapp.auth.MyUserPrincipal;
import org.springframework.security.core.Authentication;

public class SimpleController {
    String getUsername(Authentication authentication) {
        return ((MyUserPrincipal) authentication.getPrincipal()).getUsername();
    }
}
