package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.HelpService;
import ar.edu.itba.paw.webapp.form.HelpStatusForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Controller
public class HelpController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final HelpService helpService;

    public HelpController(HelpService helpService) {
        this.helpService = helpService;
    }

    @GET
    @Path("/helpList")
    public Response help(@ModelAttribute("getHelpForm") HelpStatusForm helpForm) {
        // todo: mav was "helpRequests.jsp"
        LOGGER.debug("Request attempted to get the list of help requests.");
        return Response.ok(helpService.getAllRequestsThatRequireAction()).build();
    }

    @POST
    @Path("/updateHelpStep")
    public Response updateHelpStep(@ModelAttribute("getHelpForm") HelpStatusForm helpForm) throws RequestInvalidException {
        // todo: mav was "helpRequests.jsp"
        LOGGER.debug("Attempted to update status on help request.");
        if(helpService.updateStatus(helpForm.getHelpId(), helpForm.getStatus())) {
            return Response.ok(helpService.getAllRequestsThatRequireAction()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
