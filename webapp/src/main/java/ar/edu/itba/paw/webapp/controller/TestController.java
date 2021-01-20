package ar.edu.itba.paw.webapp.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Controller
@Path("hello")
public class TestController {

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response helloWorld() {
        return Response.accepted("HELLO").build();
    }
}
