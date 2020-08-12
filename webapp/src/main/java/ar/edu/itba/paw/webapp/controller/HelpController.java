package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.HelpService;
import ar.edu.itba.paw.models.help.HelpStep;
import ar.edu.itba.paw.webapp.form.HelpStatusForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Controller
public class HelpController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final HelpService helpService;

    public HelpController(HelpService helpService) {
        this.helpService = helpService;
    }

    @GET
    @Path("/help")
    public Response help(@ModelAttribute("getHelpForm") HelpStatusForm helpForm) {
        // todo: mav was "helpRequests.jsp"
        LOGGER.debug("Request attempted to get the list of help requests.");
        return Response.ok(helpService.getAllRequestsThatRequireAction()).build();
    }

    @PUT
    @Path("/help/{helpRequestId}")
    public Response updateHelpStep(@PathParam("helpRequestId") final long helpRequestId, HelpStep status) throws RequestInvalidException {
        // todo: mav was "helpRequests.jsp"
        LOGGER.debug("Attempted to update status on help request.");
        if(helpService.updateStatus(helpRequestId, status)) {
            return Response.ok(helpService.getAllRequestsThatRequireAction()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
