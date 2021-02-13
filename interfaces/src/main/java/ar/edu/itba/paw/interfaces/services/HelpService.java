package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.help.Help;
import ar.edu.itba.paw.models.help.HelpStep;

import java.util.List;

public interface HelpService {
    String requestHelp(String text, long reservationId);

    PaginatedDTO<Help> getAllHelpRequestsByReservationId(long reservationId, int page, int pageSize) throws RequestInvalidException;

    PaginatedDTO<Help> getAllRequestsNotAttendedTo(int page, int pageSize);

    PaginatedDTO<Help> getAllRequestsThatRequireAction(int page, int pageSize);

    boolean updateStatus(long helpId, HelpStep status) throws RequestInvalidException;

    boolean setRequestToResolved(long helpId) throws RequestInvalidException;

    boolean setRequestToRequiresFurtherAction(long helpId) throws RequestInvalidException;
}
