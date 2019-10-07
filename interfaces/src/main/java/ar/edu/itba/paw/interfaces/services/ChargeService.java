package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.ChargeDTO;

import java.util.List;

public interface ChargeService {
    List<ChargeDTO> getAllChargesByReservationId(long reservationId);

    double sumCharge(long reservationId);

    List<Charge> getAllChargesNotDelivered();

    void setChargeToDelivered(long chargeId);
}
