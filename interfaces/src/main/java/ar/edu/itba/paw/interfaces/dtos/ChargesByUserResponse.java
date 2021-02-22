package ar.edu.itba.paw.interfaces.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChargesByUserResponse {
    private String productDescription;
    private long productId;
    private double productPrice;
    private int productAmount;
}
