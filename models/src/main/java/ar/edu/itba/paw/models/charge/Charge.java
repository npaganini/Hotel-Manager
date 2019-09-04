package ar.edu.itba.paw.models.charge;

import ar.edu.itba.paw.models.product.Product;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Charge {
    public long id;
    public List<Product> products = new ArrayList<>();

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }
}
