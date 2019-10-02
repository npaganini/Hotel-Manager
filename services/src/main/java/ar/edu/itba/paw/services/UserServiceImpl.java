package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class UserServiceImpl implements UserService {

    private final ProductDao productDao;
    private final ChargeDao chargeDao;
    private final UserDao userDao;

    private String[] toursList = {"City Tour"};
    private String[] classesList = {"Clase de Tango"};
    private final ReservationDao reservationDao;

    @Autowired
    public UserServiceImpl(ProductDao productDao, UserDao userDao, ChargeDao chargeDao, ReservationDao reservationDao) {
        this.productDao = productDao;
        this.chargeDao = chargeDao;
        this.userDao = userDao;
        this.reservationDao = reservationDao;
    }

    @Override
    public List<Product> getProducts() {
        return  new LinkedList<>(productDao.getAllProducts());
    }

    @Override
    public List<?> checkServicesUsed() {
        List<String> services = new LinkedList<>();
        services.addAll(Arrays.asList(toursList));
        services.addAll(Arrays.asList(classesList));
        return services;
    }

    @Override
    public List<Product> checkProductsPurchased() {
        return new LinkedList<>(productDao.getAllProducts());
    }

    @Override
    public Map<String, List<?>> checkAllExpenses() {
        List<?> minibar = checkProductsPurchased();
        List<?> services = checkServicesUsed();
        //        expenses.put("Minibar", minibar);
//        expenses.put("Other services", services);
        return new HashMap<>();
    }

    @Override
    public long getReservation(long userID) {
        return reservationDao.findLastReservationByUserId(userID).getId();
    }

    @Override
    public long getProductID(String productHash) {
        return 1;
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
