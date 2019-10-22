package ar.edu.itba.paw.models.product;

import ar.edu.itba.paw.models.SqlObject;
import ar.edu.itba.paw.models.charge.Charge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product implements SqlObject {

    public final static String KEY_ID = "id";
    public final static String KEY_DESCRIPTION = "description";
    public final static String KEY_PRICE = "price";
    public final static String KEY_FILE = "file";
    public final static String KEY_ENABLE = "enable";

    public final static String TABLE_NAME = "product";

    // tableName_keyID_seq
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(sequenceName = "product_id_seq", name = "product_id_seq", allocationSize = 1)
    private long id;

    @Column(length = 32, nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(columnDefinition="BINARY(64)")
    private byte[] file;

    @Column(nullable = false)
    private boolean enable;

    public Product(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getLong(KEY_ID);
        this.description = resultSet.getString(KEY_DESCRIPTION);
        this.price = resultSet.getDouble(KEY_PRICE);
        this.file = resultSet.getBytes(KEY_FILE);
        this.enable = resultSet.getBoolean(KEY_ENABLE);
    }

    public Product(String description, double price) {
        this.description = description;
        this.price = price;
        this.enable = true;
    }

    public Product(String description, double price, byte[] file) {
        this.description = description;
        this.price = price;
        this.file = file;
        this.enable = true;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> productToMap = new HashMap<>();
        productToMap.put(KEY_ID, getId());
        productToMap.put(KEY_DESCRIPTION, getDescription());
        productToMap.put(KEY_PRICE, getPrice());
        productToMap.put(KEY_FILE, getFile());
        productToMap.put(KEY_ENABLE, isEnable());
        return productToMap;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

}
