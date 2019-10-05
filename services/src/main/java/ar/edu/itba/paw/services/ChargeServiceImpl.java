package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.services.ChargeService;
import ar.edu.itba.paw.models.dtos.ChargeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargeServiceImpl implements ChargeService {

    private final ChargeDao chargeDao;

    public ChargeServiceImpl(ChargeDao chargeDao) {
        this.chargeDao = chargeDao;
    }

    @Override
    public List<ChargeDTO> getAllChargesByReservationId(long reservationId) {
        return chargeDao.findChargeByReservationHash(reservationId);
    }
}
