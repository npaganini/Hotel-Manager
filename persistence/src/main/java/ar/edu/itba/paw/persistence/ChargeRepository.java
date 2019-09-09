package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.models.charge.Charge;

public class ChargeRepository extends SimpleRepository<Charge> implements ChargeDao {
}
