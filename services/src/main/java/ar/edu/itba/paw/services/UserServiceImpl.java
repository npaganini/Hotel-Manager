package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.daos.ProductDao;
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

//    private List<Product> productsList;
    private String[] toursList = {"City Tour"};
    private String[] classesList = {"Clase de Tango"};

    @Autowired
    public UserServiceImpl(ProductDao productDao, UserDao userDao, ChargeDao chargeDao) {
        this.productDao = productDao;
        this.chargeDao = chargeDao;
        this.userDao = userDao;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> productList = new LinkedList<>();
        productList.addAll(productDao.getAllProducts());
        return productList;
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
        List<Product> productList = new LinkedList<>();
        productList.addAll(productDao.getAllProducts());
        return productList;
    }

    @Override
    public Map<String, List<?>> checkAllExpenses() {
        List<?> minibar = checkProductsPurchased();
        List<?> services = checkServicesUsed();
        Map<String, List<?>> expenses = new HashMap<>();
//        expenses.put("Minibar", minibar);
//        expenses.put("Other services", services);
        return expenses;
    }

    @Override
    public long getReservation(long userID) {
        return 2;
    }

    @Override
    public boolean addCharge(Charge product) {
        return chargeDao.addCharge(product);
    }
}
