package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.models.user.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Controller
public class AuthController {
    @Context
    private UriInfo uriInfo;

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
    public Response redirect(Authentication authentication) {
        String role = authentication.getAuthorities().toString();
        String home = "user";
        if (!role.contains(UserRole.CLIENT.toString())) {
            home = "rooms";
        }
        return Response.temporaryRedirect(uriInfo.getAbsolutePathBuilder().path(home + "/home").build()).build();
    }

    @GET
    @Path("/index")
    public Response redirectIndex(Authentication authentication) {
        return redirect(authentication);
    }
}
