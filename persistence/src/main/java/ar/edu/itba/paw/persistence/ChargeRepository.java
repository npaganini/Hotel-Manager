package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.models.charge.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

public class ChargeRepository extends SimpleRepository<Charge> implements ChargeDao {

    @Autowired
    public ChargeRepository(DataSource dataSource) {
        super(new JdbcTemplate(dataSource));
    }

    @Override
    RowMapper<Charge> getRawMapper() {
        return null;
    }

    @Override
    String getTableName() {
        return null;
    }

    @Override
    SimpleJdbcInsert getJdbcInsert() {
        return null;
    }
}
