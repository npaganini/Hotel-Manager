package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.product.Product;
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
    public List<RoomReservationDTO> findActiveReservation(String userEmail) {
        return reservationDao.findActiveReservation(userEmail);
    }

     @Override
    public Map<Product, Integer> checkProductsPurchasedByUserByReservationId(String userEmail, long reservationId) {
        return new HashMap<>(chargeDao.getAllChargesByUser(userEmail, reservationId));
    }


    @Override
    public boolean addCharge(Charge product) {
        return chargeDao.addCharge(product);
    }
}
