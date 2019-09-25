package ar.edu.itba.paw.webapp.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductsForm {
    private long productId;
    private String reservationId;
}
