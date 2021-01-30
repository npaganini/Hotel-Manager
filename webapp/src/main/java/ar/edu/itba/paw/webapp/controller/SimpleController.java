package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.webapp.auth.MyUserPrincipal;
import org.springframework.security.core.Authentication;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

}
