package ar.edu.itba.paw.interfaces.services;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<?> checkServicesUsed();

    List<?> checkProductsPurchased();

    Map<?, List<?>> checkAllExpenses();

//    boolean checkIn(long reservationID);
//
//    boolean checkOut(long reservationID);
//
//    boolean checkOut(int roomNumber);
//

//    boolean checkOut();
}
