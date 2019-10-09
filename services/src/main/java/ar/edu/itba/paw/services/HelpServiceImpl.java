package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.HelpDao;
import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.HelpService;
import ar.edu.itba.paw.models.help.Help;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class HelpServiceImpl implements HelpService {
    private final ReservationDao reservationDao;
    private final HelpDao helpDao;

    @Autowired
    public HelpServiceImpl(ReservationDao reservationDao, HelpDao helpDao) {
        this.reservationDao = reservationDao;
        this.helpDao = helpDao;
    }

    @Override
    public List<Help> getAllHelpRequestsByReservationId(long reservationId) throws RequestInvalidException {
        Reservation reservation = reservationDao.findById(Math.toIntExact(reservationId)).orElseThrow(EntityNotFoundException::new);
        return helpDao.findHelpRequestByReservationHash(reservation.getId());
    }

    @Override
    public List<Help> getAllRequestsNotAttendedTo() {
        return helpDao.findAllHelpRequestsNotSentFor();
    }

    @Override
    public boolean setRequestToResolved(long helpId) throws RequestInvalidException {
        Help helpRequest = helpDao.findById(Math.toIntExact(helpId)).orElseThrow(EntityNotFoundException::new);
        if(helpRequest != null) {
            return helpDao.updateToHelpRequestResolved(helpId);
        }
        throw new RequestInvalidException();
    }

    @Override
    public boolean setRequestToRequiresFurtherAction(long helpId) throws RequestInvalidException {
        Help helpRequest = helpDao.findById(Math.toIntExact(helpId)).orElseThrow(EntityNotFoundException::new);
        if(helpRequest != null) {
            return helpDao.updateRequestToRequiresFurtherAction(helpId);
        }
        throw new RequestInvalidException();
    }

    @Override
    public String requestHelp(String text, long reservationId) {
        Reservation reservation = reservationDao.findById(Math.toIntExact(reservationId)).orElseThrow(EntityNotFoundException::new);
        if(text.length() > 0 && isValidString(text)) {
            return helpDao.save(new Help(text, reservation)).getHelpText();
        }
        return null;
    }

    private boolean isValidString(String text) {
        return text.matches("^.*[^a-zA-Z0-9áéíóúüñÁÉÍÓÚÑ ].*$");
    }
}
