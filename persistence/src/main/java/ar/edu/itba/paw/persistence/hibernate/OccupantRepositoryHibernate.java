package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.OccupantDao;
import ar.edu.itba.paw.models.occupant.Occupant;
import org.springframework.stereotype.Repository;

@Repository
public class OccupantRepositoryHibernate extends SimpleRepositoryHibernate<Occupant> implements OccupantDao {
    @Override
    String getTableName() {
        return "Occupant";
    }

    @Override
    Class<Occupant> getModelClass() {
        return Occupant.class;
    }
}
