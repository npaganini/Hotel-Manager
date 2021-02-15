package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.dtos.ChargesByUserResponse;
import ar.edu.itba.paw.interfaces.dtos.ProductResponse;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.help.Help;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.user.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<ChargesByUserResponse> checkProductsPurchasedByUserByReservationId(String userEmail, long reservationId);

    PaginatedDTO<Reservation> findActiveReservations(String userEmail, int page, int pageSize);

    PaginatedDTO<ProductResponse> getProducts(int page, int pageSize);

    Charge addCharge(long productId, long reservationId) throws EntityNotFoundException;

    User getUserForReservation(String userEmail);

    Help requestHelp(String text, long reservationId) throws EntityNotFoundException;

    void rateStay(String rate, long reservationId) throws EntityNotFoundException, RequestInvalidException;
}
