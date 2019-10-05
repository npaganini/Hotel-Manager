package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.ChargeDTO;
import ar.edu.itba.paw.models.product.Product;

import java.util.List;
import java.util.Map;

public interface ChargeDao extends SimpleDao<Charge> {
    boolean addCharge(Charge product);

    Map<Product, Integer> getAllChargesByUser(String userEmail, long reservationId);

    List<ChargeDTO> findChargeByReservationHash(long reservationId);
}
