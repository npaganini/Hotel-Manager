package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.webapp.dto.LoginRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Component
@Path("login")
public class AuthController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        // todo: mav was "login.jsp"
        System.out.println("RECIBIMOS REQUEST: " + loginRequest.getUser() + " " + loginRequest.getUser());
        return Response.ok().build();
    }
}
