package ar.edu.itba.paw.models.entities;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.product.Product;
import lombok.AllArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
public class ProductChargeDto {
    private Product product;
    private Charge charge;

    public ProductChargeDto(ResultSet resultSet) throws SQLException {
        this.product = new Product(resultSet);
        this.charge = new Charge(resultSet);
    }
}
