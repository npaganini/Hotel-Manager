package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.ChargeDeliveryDTO;
import ar.edu.itba.paw.models.dtos.ProductAmountDTO;
import ar.edu.itba.paw.models.dtos.ProductChargeDTO;
import ar.edu.itba.paw.models.product.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class ChargeRepositoryHibernate extends SimpleRepositoryHibernate<Charge> implements ChargeDao {

    @Override
    public Map<Product, Integer> getAllChargesByUser(String userEmail, long reservationId) {
        final TypedQuery<ProductAmountDTO> query = em.createQuery(
                "SELECT c.product, COUNT(c.product) FROM Charge AS c " +
                        "WHERE c.reservation.userEmail = :userEmail AND c.reservation.id = :reservationId " +
                "GROUP BY c.product",
               ProductAmountDTO.class); // TODO we should no longer use DTOs
        query.setParameter("userEmail", userEmail);
        query.setParameter("reservationId", reservationId);
        final List<ProductAmountDTO> list = query.getResultList();
        final Map<Product, Integer> ans = new HashMap<>();
        for (ProductAmountDTO product : list) {
            ans.put(product.getProduct(), product.getAmount());
        }
        return ans;
    }

    @Override
    public List<Charge> findChargeByReservationHash(long reservationId) {
        return em.createQuery("SELECT c FROM Charge AS c WHERE c.reservation.id = :reservationId",
                Charge.class).getResultList();
    }

    @Override
    public double sumCharge(long reservationId) {
        final TypedQuery<Double> query = em.createQuery("SELECT SUM(c.product.price) FROM Charge AS c WHERE c.reservation.id = :reservationId", Double.class);
        query.setParameter("reservationId", reservationId);
        return query.getSingleResult();
    }

    @Override
    public List<Charge> findAllChargesNotDelivered() {
        return em.createQuery("SELECT c FROM Charge AS c WHERE c.delivered = FALSE", Charge.class).getResultList();
    }

    @Override
    public int updateChargeToDelivered(long chargeId) {
        final TypedQuery<ar.edu.itba.paw.models.charge.Charge> query = em.createQuery("SELECT c FROM Charge AS c WHERE c.id = :chargeId", Charge.class);
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
        return "Charge ";
    }


    @Override
    Class<Charge> getModelClass() {
        return Charge.class;
    }
}
