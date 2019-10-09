package ar.edu.itba.paw.models.dtos;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.product.Product;
import lombok.AllArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
public class ProductChargeDTO {
    private Product product;
    private Charge charge;

    public ProductChargeDTO(ResultSet resultSet) throws SQLException {
        this.product = new Product(resultSet);
        this.charge = new Charge(resultSet);
    }
}
