package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.entities.ProductChargeDto;
import ar.edu.itba.paw.models.product.Product;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<?> checkServicesUsed();

    Map<Product, Integer> checkProductsPurchasedByUser(long userID);

    List<Product> getProducts();

    long getReservation(long userID);

    long getReservationID(String reservationHash);

    boolean addCharge(Charge product);

}
