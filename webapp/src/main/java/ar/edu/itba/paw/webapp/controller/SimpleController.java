package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.webapp.auth.MyUserPrincipal;
import org.springframework.security.core.Authentication;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SimpleController {
    String getUsername(Authentication authentication) {
        return ((MyUserPrincipal) authentication.getPrincipal()).getUsername();
    }

    Calendar fromStringToCalendar(String date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        cal.setTime(sdf.parse(date));
        return cal;
    }

    protected String getUserEmailFromJwt() {
        throw new NotImplementedException();
    }

    protected <T> Response sendPaginatedResponse(int currentPage, int limit, PaginatedDTO<T> paginatedDTO, UriInfo uriInfo) {
        long totalCount = paginatedDTO.getMaxItems();
        return Response.ok(paginatedDTO.getList())
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", 1).build(), "first")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", totalCount % limit == 0 ? (totalCount / limit) : (totalCount / limit) + 1).build(), "last")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", currentPage > 1 ? currentPage - 1 : currentPage).build(), "prev")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", currentPage < ((double) totalCount / limit) ? currentPage + 1 : currentPage).build(), "next")
                .header("X-Total-Count", totalCount)
                .build();
    }
}
