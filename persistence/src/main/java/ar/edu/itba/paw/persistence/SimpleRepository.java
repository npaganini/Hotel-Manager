package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.SimpleDao;
import ar.edu.itba.paw.models.SqlObject;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.room.RoomType;
import ar.edu.itba.paw.models.user.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static ar.edu.itba.paw.models.SqlObject.KEY_ID;

public abstract class SimpleRepository<T extends SqlObject> implements SimpleDao<T> {

    final NamedParameterJdbcTemplate jdbcTemplateWithNamedParameter;
    final SimpleJdbcInsert simpleJdbcInsert;

    SimpleRepository(NamedParameterJdbcTemplate jdbcTemplateWithNamedParameter) {
        this.jdbcTemplateWithNamedParameter = jdbcTemplateWithNamedParameter;
        this.simpleJdbcInsert =
                new SimpleJdbcInsert(jdbcTemplateWithNamedParameter.getJdbcTemplate())
                        .withTableName(getTableName())
                        .usingGeneratedKeyColumns(KEY_ID);
        jdbcTemplateWithNamedParameter.getJdbcTemplate()
                .execute("CREATE TABLE IF NOT EXISTS " + Product.TABLE_NAME + " (" +
                        "id SERIAL PRIMARY KEY, " +
                        "description VARCHAR(150), " +
                        "price DOUBLE PRECISION);");
        jdbcTemplateWithNamedParameter.getJdbcTemplate()
                .execute("CREATE TABLE IF NOT EXISTS " + User.TABLE_NAME + " (" +
                        "id SERIAL PRIMARY KEY ," +
                        "email varchar(100));");
        jdbcTemplateWithNamedParameter.getJdbcTemplate()
                .execute("CREATE TABLE IF NOT EXISTS " + Room.TABLE_NAME + " (" +
                        "id SERIAL PRIMARY KEY, " +
                        "room_type VARCHAR(15), " +
                        "is_free_now BOOLEAN, " +
                        "number INTEGER);");
    }

    List<Room> getHardcodedRooms() {
        Room room1 = new Room(1, RoomType.DOUBLE, true, 102);
        Room room2 = new Room(2, RoomType.TRIPLE, true, 103);
        Room room3 = new Room(3, RoomType.SIMPLE, true, 104);
        Room room4 = new Room(4, RoomType.DOUBLE, true, 105);
        Room room5 = new Room(5, RoomType.DOUBLE, true, 106);
        return Arrays.asList(room1, room2, room3, room4, room5);
    }

    List<Product> getHardcodedProducts() {
        Product product = new Product(1, "Coca-Cola", 45.3d);
        Product product1 = new Product(2, "Whisky", 150d);
        Product product2 = new Product(3, "Papitas", 12d);
        Product product3 = new Product(4, "Chocolates", 19d);
        return Arrays.asList(product, product1, product2, product3);
    }

    @Override
    public T save(T t) {
        t.setId(getJdbcInsert().executeAndReturnKey(t.toMap()).longValue());
        return t;
    }

    @Override
    public Optional<T> findById(long id) {
        List<T> resultSet = jdbcTemplateWithNamedParameter.getJdbcTemplate()
                .query("SELECT * FROM " + getTableName() + " WHERE id = ?", getRowMapper(), id);
        return resultSet.size() > 0 ? Optional.of(resultSet.get(0)) : Optional.empty();
    }

    abstract RowMapper<T> getRowMapper();

    abstract String getTableName();

    abstract SimpleJdbcInsert getJdbcInsert();
}
