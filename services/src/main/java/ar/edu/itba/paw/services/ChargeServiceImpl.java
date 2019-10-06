package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.services.ChargeService;
import ar.edu.itba.paw.models.dtos.ChargeDTO;
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
    public List<ChargeDTO> getAllChargesByReservationId(long reservationId) {
        return chargeDao.findChargeByReservationHash(reservationId);
    }

    @Override
    public int sumCharge(long reservationId){
        return chargeDao.sumCharge(reservationId);
    }
}
