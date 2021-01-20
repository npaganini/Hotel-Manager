package ar.edu.itba.paw.models.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    public final static String KEY_ID = "id";
    public final static String KEY_DESCRIPTION = "description";
    public final static String KEY_PRICE = "price";
    public final static String KEY_FILE = "file";
    public final static String KEY_ENABLE = "enable";

    public final static String NAME = "Product";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "NUMERIC(19,0)")
    private long id;

    @Column(length = 32, nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    private byte[] file;

    @Column(nullable = false)
    private boolean enable;

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
    public int hashCode() {
        return Math.toIntExact(id);
    }

}
