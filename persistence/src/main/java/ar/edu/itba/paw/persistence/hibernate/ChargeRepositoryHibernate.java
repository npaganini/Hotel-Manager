package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.ProductAmountDTO;
import ar.edu.itba.paw.models.product.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ChargeRepositoryHibernate extends SimpleRepositoryHibernate<Charge> implements ChargeDao {

    @Override
    public Map<Product, Integer> getAllChargesByUser(String userEmail, long reservationId) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ProductAmountDTO> query = builder.createQuery(ProductAmountDTO.class);
        Root<Charge> charge = query.from(Charge.class);
        Predicate predicateForEmail = builder.equal(charge.get("reservation").get("userEmail"), userEmail);
        Predicate predicateForId = builder.equal(charge.get("reservation").get("id"), reservationId);
        Predicate finalPredicate = builder.and(predicateForEmail, predicateForId);
        query.multiselect(charge.get("product"), builder.count(charge));
        query.where(finalPredicate);
        query.groupBy(charge.get("product"));

        final List<ProductAmountDTO> list = em.createQuery(query).getResultList();

        final Map<Product, Integer> ans = new HashMap<>();
        for(ProductAmountDTO product: list) {
            ans.put(product.getProduct(), Math.toIntExact(product.getAmount()));
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
