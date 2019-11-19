package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.HelpDao;
import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.help.Help;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final ProductDao productDao;
    private final ChargeDao chargeDao;
    private final ReservationDao reservationDao;
    private final HelpDao helpDao;

    @Autowired
    public UserServiceImpl(ProductDao productDao, ChargeDao chargeDao, ReservationDao reservationDao, HelpDao helpDao) {
        this.productDao = productDao;
        this.chargeDao = chargeDao;
        this.reservationDao = reservationDao;
        this.helpDao = helpDao;
    }

    @Override
    public List<Product> getProducts() {
        return new LinkedList<>(productDao.findAll());
    }

    @Override
    public List<Reservation> findActiveReservation(String userEmail) {
        return reservationDao.findActiveReservationByEmail(userEmail);
    }

    @Override
    public Map<Product, Integer> checkProductsPurchasedByUserByReservationId(String userEmail, long reservationId) {
        return new HashMap<>(chargeDao.getAllChargesByUser(userEmail, reservationId));
    }


    @Override
    public Charge addCharge(long productId, long reservationId) {
        Product product = productDao.findById(Math.toIntExact(productId)).orElseThrow(EntityNotFoundException::new);
        Reservation reservation = reservationDao.findById(Math.toIntExact(reservationId)).orElseThrow(EntityNotFoundException::new);
        return chargeDao.save(new Charge(product, reservation));
    }

    @Override
    public String requestHelp(String text, long reservationId) {
        Reservation reservation = reservationDao.findById(Math.toIntExact(reservationId)).orElseThrow(EntityNotFoundException::new);
        if(text.length() > 0 && isValidString(text)) {
            return helpDao.save(new Help(text, reservation)).getHelpText();
        }
        return null;
    }

    private boolean isValidString(String text) {
        return text.matches("^.*[a-zA-Z0-9áéíóúüñÁÉÍÓÚÑ ].*$");
    }
}
