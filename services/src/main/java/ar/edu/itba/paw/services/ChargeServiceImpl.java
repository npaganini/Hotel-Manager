package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.ChargeService;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.ChargeDeliveryDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChargeServiceImpl implements ChargeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChargeServiceImpl.class);

    private final ReservationDao reservationDao;
    private final ChargeDao chargeDao;

    public ChargeServiceImpl(ReservationDao reservationDao, ChargeDao chargeDao) {
        this.reservationDao = reservationDao;
        this.chargeDao = chargeDao;
    }

    @Override
    public List<Charge> getAllChargesByReservationId(long reservationId) throws RequestInvalidException {
        LOGGER.debug("Getting all current charges for reservation with id " + reservationId);
        Optional<Reservation> reservationOptional = reservationDao.findById(reservationId);
        if (!reservationOptional.isPresent() || !reservationOptional.get().isActive()) {
            throw new RequestInvalidException();
        }
        return chargeDao.findChargeByReservationHash(reservationId);
    }

    @Override
    public double sumCharge(long reservationId) {
        LOGGER.debug("Getting the balance of reservation with id" + reservationId);
        return chargeDao.sumCharge(reservationId);
    }

    @Override
    public List<Charge> getAllChargesNotDelivered() {
        return chargeDao.findAllChargesNotDelivered();
    }

    @Override
    public void setChargeToDelivered(long chargeId) throws RequestInvalidException {
        if (chargeDao.findById(chargeId).orElseThrow(RequestInvalidException::new).isDelivered()) {
            throw new RequestInvalidException();
        }
        chargeDao.updateChargeToDelivered(chargeId);
    }
}
