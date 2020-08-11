package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.models.user.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Controller
public class AuthController {

    @GET
    @Path("/login")
    public Response login() {
        // todo: mav was "login.jsp"
        return Response.ok().build();
    }

    @GET
    @Path("/403")
    public Response forbidden() {
        // todo: mav was "403.jsp"
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Path("/")
    public String redirect(Authentication authentication) {
        String role = authentication.getAuthorities().toString();
        String home = "user";
        if (!role.contains(UserRole.CLIENT.toString())) {
            home = "rooms";
        }
        return "redirect:/" + home + "/home";
    }

    @GET
    @Path("/index")
    public String redirectIndex(Authentication authentication) {
        return redirect(authentication);
    }
}
