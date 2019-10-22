package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.ChargeDeliveryDTO;
import ar.edu.itba.paw.models.dtos.ChargeRoomReservationDTO;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class ChargeRepositoryHibernate implements ChargeDao {
    @PersistenceContext
    private EntityManager em;

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
        for(Charge charge: list) {
            ans.merge(charge.getProductPurchased(), 1, Integer::sum);
        }
        return ans;
    }
//    FROM charge c JOIN product p " +
//    "ON c.product_id = p.id JOIN reservation r ON r.id = c.reservation_id " +
//    "WHERE r.user_email = :email AND r.id = :reservationId group by p.id"

//    final TypedQuery<User> query = em.createQuery("from User as u where u.email = :userEmail", User.class);
//        query.setParameter("userEmail", userEmail);
//    final List<User> list = query.getResultList();
//        return Optional.ofNullable(list.get(0));    // returns null if list is empty (no user with that username found)

    @Override
    public List<ChargeRoomReservationDTO> findChargeByReservationHash(long reservationId) {
        final TypedQuery<Charge> query = em.createQuery("FROM Charge AS c WHERE c.reservationToCharge.id = :reservationId", Charge.class);
        query.setParameter("reservationId", reservationId);
        final List<Charge> list = query.getResultList();
        final List<ChargeRoomReservationDTO> ans = new LinkedList<>();
        for(Charge charge: list) {
            ans.add(new ChargeRoomReservationDTO(charge.getProductPurchased(), charge, charge.getReservationToCharge()));
        }
        return ans;
    }

    @Override
    public double sumCharge(long reservationId) {
        final TypedQuery<Charge> query = em.createQuery("FROM Charge AS c WHERE c.reservationToCharge.id = :reservationId", Charge.class);
        query.setParameter("reservationId", reservationId);
        final List<Charge> list = query.getResultList();
        double ans = 0;
        for(Charge charge: list) {
            ans += charge.getProductPurchased().getPrice();
        }
        return ans;
    }

    @Override
    public List<ChargeDeliveryDTO> findAllChargesNotDelivered() {
        final TypedQuery<Charge> query = em.createQuery("FROM Charge AS c WHERE c.delivered = FALSE", Charge.class);
        final List<Charge> list = query.getResultList();
        List<ChargeDeliveryDTO> ans = new LinkedList<>();
        for(Charge charge: list) {
            ans.add(new ChargeDeliveryDTO(charge.getId(), charge.getReservationToCharge().getAssignedRoom().getNumber(), false, charge.getProductPurchased().getDescription()));
        }
        return ans;
    }

    @Override
    public int updateChargeToDelivered(long chargeId) {
        final TypedQuery<Charge> query = em.createQuery("FROM Charge AS c WHERE c.id = :chargeId", Charge.class);
        query.setParameter("chargeId", chargeId);
        final Charge charge = query.getSingleResult();
        if(charge != null) {
            charge.setProductDelivered();
            em.merge(charge);
            return 1;
        }
        return 0;
    }

    @Override
    public Charge save(Charge charge) {
        final Charge chargeToAdd = new Charge(charge.getProductPurchased(), charge.getReservationToCharge());
        em.persist(chargeToAdd);
        return chargeToAdd;
    }

    @Override
    public Optional<Charge> findById(long id) {
        return Optional.ofNullable(em.find(Charge.class, id));
    }

    @Override
    public List<Charge> findAll() {
        return em.createQuery("from Charge", Charge.class).getResultList();
    }
}
