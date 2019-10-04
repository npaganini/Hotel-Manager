package ar.edu.itba.paw.models.dtos;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChargeDTO {
    private Product product;
    private Charge charge;
    private Reservation reservation;

    public ChargeDTO(ResultSet resultSet) throws SQLException {
        this.product = new Product(resultSet);
        this.charge = new Charge(resultSet);
        this.reservation = new Reservation(resultSet);
    }
}
