package ar.edu.itba.paw.models.product;

import ar.edu.itba.paw.models.SqlObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Product implements SqlObject {

    public final static String KEY_ID = "id";
    public final static String KEY_DESCRIPTION = "description";
    public final static String KEY_PRICE = "price";
    public final static String KEY_FILE_PATH = "file_path";
    public final static String KEY_ENABLE = "enable";

    public final static String TABLE_NAME = "product";

    private long id;
    private String description;
    private double price;
    private String filePath;
    private boolean enable;

    public Product(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getLong(KEY_ID);
        this.description = resultSet.getString(KEY_DESCRIPTION);
        this.price = resultSet.getDouble(KEY_PRICE);
        this.filePath = resultSet.getString(KEY_FILE_PATH);
        this.enable = resultSet.getBoolean(KEY_ENABLE);
    }

    public Product(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public Product(String description, double price, String filePath) {
        this.description = description;
        this.price = price;
        this.filePath = filePath;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> productToMap = new HashMap<>();
        productToMap.put(KEY_ID, getId());
        productToMap.put(KEY_DESCRIPTION, getDescription());
        productToMap.put(KEY_PRICE, getPrice());
        productToMap.put(KEY_FILE_PATH, getFilePath());
        productToMap.put(KEY_ENABLE, isEnable());
        return productToMap;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

}
