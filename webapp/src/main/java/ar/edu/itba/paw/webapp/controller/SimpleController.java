package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.webapp.auth.MyUserPrincipal;
import org.springframework.security.core.Authentication;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
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

    protected <T extends Serializable> Response sendPaginatedResponse(int currentPage, int limit, long totalCount, GenericEntity<List<T>> paginatedDtoList, UriBuilder uriBuilder) {
        return Response.ok(paginatedDtoList)
            .link(uriBuilder.replaceQueryParam("page", 1).build(), "first")
            .link(uriBuilder.replaceQueryParam("page", totalCount % limit == 0 ? (totalCount / limit) : (totalCount / limit) + 1).build(), "last")
            .link(uriBuilder.replaceQueryParam("page", currentPage > 1 ? currentPage - 1 : currentPage).build(), "prev")
            .link(uriBuilder.replaceQueryParam("page", currentPage < ((double) totalCount / limit) ? currentPage + 1 : currentPage).build(), "next")
            .header("X-Total-Count", totalCount)
            .build();
    }
}
