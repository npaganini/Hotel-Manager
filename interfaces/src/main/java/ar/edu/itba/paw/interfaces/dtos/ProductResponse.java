package ar.edu.itba.paw.interfaces.dtos;

import ar.edu.itba.paw.models.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductResponse implements Serializable {
    private long id;
    private String description;
    private double price;

    public static ProductResponse fromProduct(Product product) {
        final ProductResponse pDto = new ProductResponse();

        pDto.id = product.getId();
        pDto.description = product.getDescription();
        pDto.price = product.getPrice();

        return pDto;
    }
}
