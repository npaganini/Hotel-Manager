package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.services.UserService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserServiceImpl implements UserService {

    private String[] productsList = {"Coca-Cola", "Papas Dia"};
    private String[] toursList = {"City Tour"};
    private String[] classesList = {"Clase de Tango"};


    @Override
    public List<?> checkServicesUsed() {
        List<String> services = new LinkedList<>();
        services.addAll(Arrays.asList(toursList));
        services.addAll(Arrays.asList(classesList));
        return services;
    }

    @Override
    public List<?> checkProductsPurchased() {
        return new LinkedList<>(Arrays.asList(productsList));
    }

    @Override
    public Map<String, List<?>> checkAllExpenses() {
        List<?> minibar = checkProductsPurchased();
        List<?> services = checkServicesUsed();
        Map<String, List<?>> expenses = new HashMap<>();
        expenses.put("Minibar", minibar);
        expenses.put("Other services", services);
        return expenses;
    }

//    @Override
//    public boolean checkIn(long reservationID) {
//        return false;
//    }
//
//    @Override
//    public boolean checkOut(long reservationID) {
//        return false;
//    }
//
//    @Override
//    public boolean checkOut(int roomNumber) {
//        return false;
//    }
//
//    @Override
//    public boolean checkOut() {
//        return false;

//    }
}
