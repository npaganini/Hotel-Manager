package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.ChargeDeliveryDTO;
import ar.edu.itba.paw.models.product.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class ChargeRepositoryHibernate extends SimpleRepositoryHibernate<Charge> implements ChargeDao {

    //FIXME esto no me gusta nada
    @Override
    public Map<Product, Integer> getAllChargesByUser(String userEmail, long reservationId) {
        final TypedQuery<Charge> query = em.createQuery(
                "FROM Charge AS c " +
                        "WHERE c.reservationToCharge.userEmail = :userEmail AND c.reservationToCharge.id = :reservationId",
               Charge.class);
        query.setParameter("userEmail", userEmail);
        query.setParameter("reservationId", reservationId);
        final List<Charge> list = query.getResultList();
        final Map<Product, Integer> ans = new HashMap<>();
        for (Charge charge : list) {
            ans.merge(charge.getProduct(), 1, Integer::sum);
        }
        return ans;
    }

    @Override
    public List<Charge> findChargeByReservationHash(long reservationId) {
        return em.createQuery("FROM Charge AS c WHERE c.reservationToCharge.id = :reservationId",
                Charge.class).getResultList();
    }

    //FIXME esto es realmente feo, para que postgres tiene un sum?
    @Override
    public double sumCharge(long reservationId) {
        final TypedQuery<Charge> query = em.createQuery("FROM Charge AS c WHERE c.reservationToCharge.id = :reservationId", Charge.class);
        query.setParameter("reservationId", reservationId);
        final List<Charge> list = query.getResultList();
        double ans = 0;
        for (ar.edu.itba.paw.models.charge.Charge charge : list) {
            ans += charge.getProduct().getPrice();
        }
        return ans;
    }

    @Override
    public List<ChargeDeliveryDTO> findAllChargesNotDelivered() {
        return em.createQuery("FROM Charge AS c WHERE c.delivered = FALSE", ChargeDeliveryDTO.class).getResultList();
    }

    @Override
    public int updateChargeToDelivered(long chargeId) {
        final TypedQuery<ar.edu.itba.paw.models.charge.Charge> query = em.createQuery("FROM Charge AS c WHERE c.id = :chargeId", Charge.class);
        query.setParameter("chargeId", chargeId);
        final Charge charge = query.getSingleResult();
        if (charge != null) {
            charge.setProductDelivered();
            em.merge(charge);
            return 1;
        }
        return 0;
    }


    @Override
    String getTableName() {
        return Charge.TABLE_NAME;
    }

    @Override
    Class<Charge> getModelClass() {
        return Charge.class;
    }
}
