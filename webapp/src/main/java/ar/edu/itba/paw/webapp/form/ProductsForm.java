package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.models.charge.Charge;
import lombok.Getter;

@Getter
public class ProductsForm {
    private long productId;
    private int quantity;
    private long reservationId;
}
