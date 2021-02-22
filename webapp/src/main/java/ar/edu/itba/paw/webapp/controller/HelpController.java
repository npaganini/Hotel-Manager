package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.dtos.HelpResponse;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.HelpService;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.help.HelpStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Controller
@Path("help")
public class HelpController extends SimpleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelpController.class);
    public static final String DEFAULT_FIRST_PAGE = "1";
    public static final String DEFAULT_PAGE_SIZE = "20";

    private final HelpService helpService;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public HelpController(HelpService helpService) {
        this.helpService = helpService;
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response help(@QueryParam("page") @DefaultValue(DEFAULT_FIRST_PAGE) int page,
                         @QueryParam("limit") @DefaultValue(DEFAULT_PAGE_SIZE) int limit) {
        // todo: mav was "helpRequests.jsp"
        LOGGER.info("Request attempted to get the list of help requests.");
        PaginatedDTO<HelpResponse> helpRequests;
        try {
            helpRequests = helpService.getAllRequestsThatRequireAction(page, limit);
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return sendPaginatedResponse(page, limit, helpRequests.getMaxItems(), helpRequests.getList(), uriInfo.getAbsolutePathBuilder());
    }

    @PUT
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response updateHelpStep(@PathParam("id") final long helpRequestId, @QueryParam("getHelpFormStatus") HelpStep status,
                                   @QueryParam("page") @DefaultValue(DEFAULT_FIRST_PAGE) int page,
                                   @QueryParam("limit") @DefaultValue(DEFAULT_PAGE_SIZE) int limit) throws RequestInvalidException {
        // todo: mav was "helpRequests.jsp"
        LOGGER.info("Attempted to update status on help request.");
        try {
            if (helpService.updateStatus(helpRequestId, status)) {
                return help(page, limit);
            }
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
