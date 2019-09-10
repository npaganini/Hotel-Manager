package ar.edu.itba.paw.models.product;

import ar.edu.itba.paw.models.SqlObject;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Product implements SqlObject {

    private long id;
    private String description;
    private double price;

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> productToMap = new HashMap<>();
        productToMap.put("id", getId());
        productToMap.put("description", getDescription());
        productToMap.put("price", getPrice());
        return productToMap;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
