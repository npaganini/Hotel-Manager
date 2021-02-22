package ar.edu.itba.paw.webapp.controller;

import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Component
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    // TODO for every exception it should be one mapper to a different status and message
    @Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace();
        return Response.status(500).build();
    }
}
