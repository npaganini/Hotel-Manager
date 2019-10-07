package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.dtos.ChargeRoomReservationDTO;

import java.util.List;

public interface ChargeService {
    List<ChargeRoomReservationDTO> getAllChargesByReservationId(long reservationId);

    double sumCharge(long reservationId);

    List<ChargeRoomReservationDTO> getAllChargesNotDelivered();

    void setChargeToDelivered(long chargeId);
}
