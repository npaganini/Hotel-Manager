package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.models.charge.Charge;

import java.util.List;

public interface ChargeService {
    List<Charge> getAllChargesByReservationId(long reservationId) throws RequestInvalidException;

    double sumCharge(long reservationId) throws RequestInvalidException;

    List<Charge> getAllChargesNotDelivered();

    int setChargeToDelivered(long chargeId) throws RequestInvalidException, EntityNotFoundException;

    int setChargesToDelivered(long roomId) throws RequestInvalidException, EntityNotFoundException;
}
