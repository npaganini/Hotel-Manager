package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.services.ChargeService;
import ar.edu.itba.paw.models.dtos.ChargeDeliveryDTO;
import ar.edu.itba.paw.models.dtos.ChargeRoomReservationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargeServiceImpl implements ChargeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChargeServiceImpl.class);

    private final ChargeDao chargeDao;

    public ChargeServiceImpl(ChargeDao chargeDao) {
        this.chargeDao = chargeDao;
    }

    @Override
    public List<ChargeRoomReservationDTO> getAllChargesByReservationId(long reservationId) {
        LOGGER.debug("Getting all current charges for reservation with id " + reservationId);
        return chargeDao.findChargeByReservationHash(reservationId);
    }

    @Override
    public double sumCharge(long reservationId) {
        LOGGER.debug("Getting the balance of reservation with id" + reservationId);
        return chargeDao.sumCharge(reservationId);
    }

    @Override
    public List<ChargeDeliveryDTO> getAllChargesNotDelivered() {
        return chargeDao.findAllChargesNotDelivered();
    }

    @Override
    public void setChargeToDelivered(long chargeId) {
        chargeDao.updateChargeToDelivered(chargeId);
    }
}
