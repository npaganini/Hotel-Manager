package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.dtos.ChargesByUserResponse;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;

import java.util.List;

public interface ChargeService {
    List<Charge> getAllChargesByReservationId(long reservationId) throws RequestInvalidException;

    PaginatedDTO<Charge> getAllChargesByReservationId(long reservationId, int page, int pageSize) throws RequestInvalidException;

    double sumCharge(long reservationId) throws RequestInvalidException;

    PaginatedDTO<Charge> getAllChargesNotDelivered(int page, int pageSize);

    int setChargeToDelivered(long chargeId) throws RequestInvalidException, EntityNotFoundException;

    int setChargesToDelivered(long roomId) throws RequestInvalidException, EntityNotFoundException;

    List<ChargesByUserResponse> checkProductsPurchasedInCheckOut(long reservationId);
}
