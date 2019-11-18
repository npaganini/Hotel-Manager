package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.user.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<Product, Integer> checkProductsPurchasedByUserByReservationId(String userEmail, long reservationId);

    List<Reservation> findActiveReservations(String userEmail);

    List<Product> getProducts();

    Charge addCharge(long productId, long reservationId) throws EntityNotFoundException;

    User getUserForReservation(String userEmail);
    
    String requestHelp(String text, long reservationId);
}
