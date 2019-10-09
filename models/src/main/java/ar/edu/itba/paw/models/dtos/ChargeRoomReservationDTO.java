package ar.edu.itba.paw.models.dtos;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
public class ChargeRoomReservationDTO {
    private Product product;
    private Charge charge;
    private Reservation reservation;

    public ChargeRoomReservationDTO(ResultSet resultSet) throws SQLException {
        this.product = new Product(resultSet);
        this.charge = new Charge(resultSet);
        this.reservation = new Reservation(resultSet);
    }
}
