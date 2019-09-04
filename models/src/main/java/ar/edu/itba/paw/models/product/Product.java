package ar.edu.itba.paw.models.product;

import lombok.Getter;

@Getter
public class Product {
    private long id;
    private String description;
    private double price;

    public Product(String description, double price) {
        this.description = description;
        this.price = price;
    }
}
