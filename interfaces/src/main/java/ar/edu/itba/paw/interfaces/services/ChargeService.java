package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.dtos.ChargeDeliveryDTO;
import ar.edu.itba.paw.models.dtos.ChargeRoomReservationDTO;

import java.util.List;

public interface ChargeService {
    List<ChargeRoomReservationDTO> getAllChargesByReservationId(long reservationId) throws Exception;

    double sumCharge(long reservationId) throws Exception;

    List<ChargeDeliveryDTO> getAllChargesNotDelivered();

    void setChargeToDelivered(long chargeId) throws Exception;
}
