package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.dtos.ChargeDTO;
import ar.edu.itba.paw.models.entities.ProductChargeDto;

import java.util.List;
import java.util.Map;

public interface ChargeDao extends SimpleDao<Charge> {
    boolean addCharge(Charge product);

    Map<Product, Integer> getAllChargesByUser(long userID);
    List<ChargeDTO> findChargeByReservationHash(long reservationId);
    List<ProductChargeDto> getAllChargesByUser(long userID);
}
