package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.help.Help;

import java.util.List;

public interface HelpDao extends SimpleDao<ar.edu.itba.paw.models.help.Help> {
    List<Help> findHelpRequestByReservationHash(long reservationId);

    List<Help> findAllHelpRequestsNotSentFor();

    List<Help> findAllHelpRequestsNotResolved();

    boolean updateToHelpRequestResolved(long helpId);

    boolean updateRequestToRequiresFurtherAction(long helpId);
}
