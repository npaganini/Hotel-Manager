package ar.edu.itba.paw.models.dtos;

import ar.edu.itba.paw.models.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductAmountDTO {
    Product product;
    Long amount;
}
