package ar.edu.itba.paw.webapp.controller;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import java.io.Serializable;
import java.util.List;

public class SimpleController {
    protected String getUserEmailFromJwt(final SecurityContext securityContext) {
        return securityContext.getUserPrincipal().getName();
    }

    protected <T extends Serializable> Response sendPaginatedResponse(int currentPage, int limit, long totalCount, List<T> paginatedDtoList, UriBuilder uriBuilder) {
        return Response.ok(paginatedDtoList)
                .link(uriBuilder.replaceQueryParam("page", 1).build(), "first")
                .link(uriBuilder.replaceQueryParam("page", totalCount % limit == 0 ? (totalCount / limit) : (totalCount / limit) + 1).build(), "last")
                .link(uriBuilder.replaceQueryParam("page", currentPage > 1 ? currentPage - 1 : currentPage).build(), "prev")
                .link(uriBuilder.replaceQueryParam("page", currentPage < ((double) totalCount / limit) ? currentPage + 1 : currentPage).build(), "next")
                .header("X-Total-Count", totalCount)
                .build();
    }
}
