package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.product.Product;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<Product, Integer> checkProductsPurchasedByUserByReservationId(String userEmail, long reservationId);

    List<RoomReservationDTO> findActiveReservation(String userEmail);

    List<Product> getProducts();

    boolean addCharge(Charge product);

}
