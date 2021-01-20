package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.HelpService;
import ar.edu.itba.paw.models.help.HelpStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("help")
public class HelpController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelpController.class);

    private final HelpService helpService;

    public HelpController(HelpService helpService) {
        this.helpService = helpService;
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response help() {
        // todo: mav was "helpRequests.jsp"
        LOGGER.debug("Request attempted to get the list of help requests.");
        return Response.ok(helpService.getAllRequestsThatRequireAction()).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response updateHelpStep(@PathParam("id") final long helpRequestId, @QueryParam("getHelpFormStatus") HelpStep status) throws RequestInvalidException {
        // todo: mav was "helpRequests.jsp"
        LOGGER.debug("Attempted to update status on help request.");
        if(helpService.updateStatus(helpRequestId, status)) {
            return Response.ok(helpService.getAllRequestsThatRequireAction()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
