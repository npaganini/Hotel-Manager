package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.models.user.UserRole;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component
@Path("/")
public class AuthController {

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("/403")
    public Response forbidden() {
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    public Response redirect() {
        return redirectHome();
    }

    @GET
    @Path("/index")
    public Response redirectIndex() {
        return redirectHome();
    }

    private Response redirectHome() {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (role == null) {
            return forbidden();
        }
        String home = "user";
        if (!role.contains(UserRole.CLIENT.toString())) {
            home = "rooms";
        }
        return Response.temporaryRedirect(uriInfo.getBaseUriBuilder().path(home).build()).build();
    }
}
