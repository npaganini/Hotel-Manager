package ar.edu.itba.paw.interfaces.dtos;

import ar.edu.itba.paw.models.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChargeResponse {
    private long id;
    private boolean delivered;
    private Product product;
}
