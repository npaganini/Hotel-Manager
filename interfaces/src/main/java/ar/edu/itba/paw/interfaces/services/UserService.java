package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<Product, Integer> checkProductsPurchasedByUserByReservationId(String userEmail, long reservationId);

    List<Reservation> findActiveReservation(String userEmail);

    List<Product> getProducts();

    Charge addCharge(long productId, long reservationId);
}
