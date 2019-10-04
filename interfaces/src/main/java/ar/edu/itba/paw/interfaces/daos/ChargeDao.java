package ar.edu.itba.paw.interfaces.daos;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.entities.ProductChargeDto;

import java.util.List;

public interface ChargeDao extends SimpleDao<Charge> {
    boolean addCharge(Charge product);

    List<ProductChargeDto> getAllChargesByUser(long userID);
}
