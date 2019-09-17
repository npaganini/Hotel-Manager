package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.interfaces.daos.ProductDao;
import ar.edu.itba.paw.models.product.Product;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<?> checkServicesUsed();

    List<Product> checkProductsPurchased();

    Map<?, List<?>> checkAllExpenses();

//    boolean checkIn(long reservationID);
//
//    boolean checkOut(long reservationID);
//
//    boolean checkOut(int roomNumber);
//
//    boolean checkOut();
}
