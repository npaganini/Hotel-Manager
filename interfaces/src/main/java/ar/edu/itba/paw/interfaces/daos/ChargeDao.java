package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.product.Product;

import java.util.List;
import java.util.Map;

public interface ChargeDao extends SimpleDao<ar.edu.itba.paw.models.charge.Charge> {
    Map<Product, Integer> getAllChargesByUser(String userEmail, long reservationId);

    List<Charge> findChargesByReservationId(long reservationId);

    PaginatedDTO<Charge> findChargesByReservationId(long reservationId, int page, int pageSize);

    double sumCharge(long reservationId);

    PaginatedDTO<Charge> findAllChargesNotDelivered(int page, int pageSize);

    int updateChargeToDelivered(long chargeId);

    int updateChargesToDelivered(long roomId);

    List<Charge> findChargesByRoomNumber(int roomNumber);

    PaginatedDTO<Charge> findChargesByRoomNumber(int roomNumber, int page, int pageSize);
}
