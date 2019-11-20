package ar.edu.itba.paw.models.dtos;

import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.product.Product;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductChargeDTO {
    private Product product;
    private Charge charge;

}
