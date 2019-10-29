package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.ChargeDeliveryDTO;

import java.util.List;

public interface ChargeService {
    List<Charge> getAllChargesByReservationId(long reservationId) throws RequestInvalidException;

    double sumCharge(long reservationId) throws RequestInvalidException;

    List<ChargeDeliveryDTO> getAllChargesNotDelivered();

    void setChargeToDelivered(long chargeId) throws RequestInvalidException;
}
