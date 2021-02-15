package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.*;
import ar.edu.itba.paw.interfaces.dtos.ChargesByUserResponse;
import ar.edu.itba.paw.interfaces.dtos.ProductResponse;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.help.Help;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    public static final int GENERATED_PASSWORD_LENGTH = 8;

    private final ProductDao productDao;
    private final ChargeDao chargeDao;
    private final ReservationDao reservationDao;
    private final UserDao userDao;
    private final HelpDao helpDao;
    private final EmailService emailService;

    @Autowired
    public UserServiceImpl(ProductDao productDao, ChargeDao chargeDao, ReservationDao reservationDao, UserDao userDao, HelpDao helpDao, EmailService emailService) {
        this.productDao = productDao;
        this.chargeDao = chargeDao;
        this.reservationDao = reservationDao;
        this.userDao = userDao;
        this.helpDao = helpDao;
        this.emailService = emailService;
    }

    @Override
    public PaginatedDTO<ProductResponse> getProducts(int page, int pageSize) {
        if (pageSize < 1 || page < 1) throw new IndexOutOfBoundsException("Pagination requested invalid.");
        PaginatedDTO<Product> products = productDao.findAllActive(page, pageSize);
        return new PaginatedDTO<>(products.getList()
                .stream().map(ProductResponse::fromProduct).collect(Collectors.toList()), products.getMaxItems());
    }

    @Override
    public PaginatedDTO<Reservation> findActiveReservations(String userEmail, int page, int pageSize) {
        if (pageSize < 1 || page < 1) throw new IndexOutOfBoundsException("Pagination requested invalid.");
        return reservationDao.findActiveReservationsByEmail(userEmail, page, pageSize);
    }

    @Override
    public List<ChargesByUserResponse> checkProductsPurchasedByUserByReservationId(String userEmail, long reservationId) {
        Map<Product, Integer> productToQtyMap = chargeDao.getAllChargesByUser(userEmail, reservationId);
        return productToQtyMap.keySet().stream().map(
                product -> new ChargesByUserResponse(product.getDescription(), product.getId(), product.getPrice(), productToQtyMap.get(product))
        ).collect(Collectors.toList());
    }

    @Override
    public Charge addCharge(long productId, long reservationId) throws EntityNotFoundException {
        Product product = productDao.findById(productId).orElseThrow(
                () -> new EntityNotFoundException("Can't find product with id " + productId));
        Reservation reservation = reservationDao.findById(reservationId).orElseThrow(
                () -> new EntityNotFoundException("Can't find reservation with id " + reservationId));
        return chargeDao.save(new Charge(product, reservation));
    }

    @Override
    public User getUserForReservation(String userEmail) {
        User user;
        Optional<User> userOptional = userDao.findByEmail(userEmail);
        if (userOptional.isPresent()) {
            user = userOptional.get();
            LOGGER.debug("There is already an user created with email " + userEmail);
        } else {
            LOGGER.debug("There is no user created with email " + userEmail + ". So we'll create one.");
            String randomPassword = generatePassword();
            System.out.println("Password for user is: " + randomPassword);  // TODO: ERASE THIS PRINT BEFORE SENDING TO PROD
            user = userDao.save(new User(userEmail, userEmail, new BCryptPasswordEncoder().encode(randomPassword)));
            LOGGER.debug("User created! Sending e-mail about user creation to: " + userEmail);
            emailService.sendUserCreatedEmail(userEmail, randomPassword);
        }
        return user;
    }

    @Override
    public Help requestHelp(String text, long reservationId) throws EntityNotFoundException {
        Reservation reservation = reservationDao.findById(reservationId).orElseThrow(() -> new EntityNotFoundException("Can't find reservation"));
        if (text.length() > 0 && isValidString(text)) {
            return helpDao.save(new Help(text, reservation));
        }
        return null;
    }

    @Transactional
    @Override
    public void rateStay(String rate, long reservationId) throws EntityNotFoundException, RequestInvalidException {
        Reservation reservation = reservationDao.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation was not found"));
        if (reservation.getCalification() != null) {
            throw new RequestInvalidException();
        }
        reservationDao.rateStay(reservation.getId(), rate);
    }

    private boolean isValidString(String text) {
        return text.matches("^.*[a-zA-Z0-9áéíóúüñÁÉÍÓÚÑ ].*$");
    }

    private Integer[] generateRandomIntsArray() {
        Random random = new SecureRandom();
        Integer[] ints = new Integer[GENERATED_PASSWORD_LENGTH];
        for (int i = 0; i < GENERATED_PASSWORD_LENGTH; i++) {
            ints[i] = random.nextInt(26);
        }
        return ints;
    }

    protected String generatePassword() {
        LOGGER.debug("Generating password...");
        Integer[] ints = generateRandomIntsArray();
        StringBuilder password = new StringBuilder(GENERATED_PASSWORD_LENGTH);
        for (Integer i : ints) {
            password.append(Character.toChars('a' + i));
        }
        return password.toString();
    }
}
