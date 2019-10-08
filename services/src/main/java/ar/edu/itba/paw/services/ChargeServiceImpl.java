package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.services.ChargeService;
import ar.edu.itba.paw.models.dtos.ChargeDeliveryDTO;
import ar.edu.itba.paw.models.dtos.ChargeRoomReservationDTO;
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
    public List<ChargeRoomReservationDTO> getAllChargesByReservationId(long reservationId) throws Exception {
        LOGGER.debug("Getting all current charges for reservation with id " + reservationId);
        Optional<Reservation> reservationOptional = reservationDao.findById(reservationId);
        if (!reservationOptional.isPresent()) { //TODO quizas haya que agregar tambien si está activa?
            throw new Exception();
        }
        return chargeDao.findChargeByReservationHash(reservationId);
    }

    @Override
    public double sumCharge(long reservationId) throws Exception {
        LOGGER.debug("Getting the balance of reservation with id" + reservationId);
        if (!chargeDao.findById(reservationId).isPresent()) {//TODO quizas haya que agregar tambien si está activa?
            throw new Exception(); // TODO
        }
        return chargeDao.sumCharge(reservationId);
    }

    @Override
    public List<ChargeDeliveryDTO> getAllChargesNotDelivered() {
        return chargeDao.findAllChargesNotDelivered();
    }

    @Override
    public void setChargeToDelivered(long chargeId) throws Exception {
        if (chargeDao.findById(chargeId).orElseThrow(Exception::new).isDelivered()) {
            throw new Exception();
        }
        chargeDao.updateChargeToDelivered(chargeId);
    }
}
