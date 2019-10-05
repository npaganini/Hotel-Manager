package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.entities.ProductChargeDto;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class UserServiceImpl implements UserService {

    private final ProductDao productDao;
    private final ChargeDao chargeDao;
   private final ReservationDao reservationDao;

    @Autowired
    public UserServiceImpl(ProductDao productDao, ChargeDao chargeDao, ReservationDao reservationDao) {
        this.productDao = productDao;
        this.chargeDao = chargeDao;
        this.reservationDao = reservationDao;
    }

    @Override
    public List<Product> getProducts() {
        return  new LinkedList<>(productDao.getAllProducts());
    }

    

    @Override
    public List<Reservation> getAllReservations(long userID) {
        return new LinkedList<>(reservationDao.findAllReservationsByUserId(userID));
    }

     @Override
    public Map<Product, Integer> checkProductsPurchasedByUser(long userID) {
        return new HashMap<>(chargeDao.getAllChargesByUser(userID));
    }

    @Override
    public long getReservation(long userID) {
        return reservationDao.findLastReservationByUserId(userID).getId();
    }

    @Override
    public long getReservationID(String reservationHash) {
        return reservationDao.findReservationByHash(reservationHash).getId();
    }

    @Override
    public boolean addCharge(Charge product) {
        return chargeDao.addCharge(product);
    }
}
