package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.models.help.Help;

import java.util.List;

public interface HelpService {
    String requestHelp(String text, long reservationId);

    List<Help> getAllHelpRequestsByReservationId(long reservationId) throws RequestInvalidException;

    List<Help> getAllRequestsNotAttendedTo();

    boolean setRequestToResolved(long helpId) throws RequestInvalidException;

    boolean setRequestToRequiresFurtherAction(long helpId) throws RequestInvalidException;
}
