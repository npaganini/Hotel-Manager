package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.dtos.HelpResponse;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.help.HelpStep;

public interface HelpService {
    String requestHelp(String text, long reservationId);

    PaginatedDTO<HelpResponse> getAllHelpRequestsByReservationId(long reservationId, int page, int pageSize) throws RequestInvalidException;

    PaginatedDTO<HelpResponse> getAllRequestsNotAttendedTo(int page, int pageSize);

    PaginatedDTO<HelpResponse> getAllRequestsThatRequireAction(int page, int pageSize);

    boolean updateStatus(long helpId, HelpStep status) throws RequestInvalidException;

    boolean setRequestToResolved(long helpId) throws RequestInvalidException;

    boolean setRequestToRequiresFurtherAction(long helpId) throws RequestInvalidException;
}
