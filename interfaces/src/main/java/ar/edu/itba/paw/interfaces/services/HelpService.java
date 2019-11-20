package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.models.help.Help;
import ar.edu.itba.paw.models.help.HelpStep;

import java.util.List;

public interface HelpService {
    String requestHelp(String text, long reservationId);

    List<Help> getAllHelpRequestsByReservationId(long reservationId) throws RequestInvalidException;

    List<Help> getAllRequestsNotAttendedTo();

    List<Help> getAllRequestsThatRequireAction();

    boolean updateStatus(long helpId, HelpStep status) throws RequestInvalidException;

    boolean setRequestToResolved(long helpId) throws RequestInvalidException;

    boolean setRequestToRequiresFurtherAction(long helpId) throws RequestInvalidException;
}
