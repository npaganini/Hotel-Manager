package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.product.Product;

import java.util.List;
import java.util.Map;

public interface ChargeDao extends SimpleDao<ar.edu.itba.paw.models.charge.Charge> {
    Map<Product, Integer> getAllChargesByUser(String userEmail, long reservationId);

    List<Charge> findChargeByReservationHash(long reservationId);

    double sumCharge(long reservationId);

    List<Charge> findAllChargesNotDelivered();

    int updateChargeToDelivered(long chargeId);
}
