package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.ProductAmountDTO;
import ar.edu.itba.paw.models.occupant.Occupant;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
public class ReservationRepositoryHibernate extends SimpleRepositoryHibernate<Reservation> implements ReservationDao {

    @Override
    public Optional<Reservation> findReservationByHash(String hash) {
        return Optional.of(
                em.createQuery("SELECT r FROM " + getTableName() + " r WHERE r.hash = :hash", getModelClass())
                        .setParameter("hash", hash)
                        .getSingleResult()
        );
    }

  /*  CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<Reservation> query = builder.createQuery(Reservation.class);
    Root<Reservation> root = query.from(Reservation.class);
    Predicate predicateDates = null;
    Predicate predicateEmail = null;
    Predicate predicateOccupant = null;

    CriteriaQuery<Occupant> queryOccupant = builder.createQuery(Occupant.class);
    Root<Occupant> occupantRoot = query.from(Occupant.class);

        if (startDate != null && endDate != null) {
        predicateDates = builder.and(builder.greaterThanOrEqualTo(root.get("startDate"),
                startDate), builder.lessThanOrEqualTo(root.get("endDate"), endDate);
    }
        if (email != null) {
        predicateEmail = builder.equal(root.get("userEmail"), email);
    }
        if (occupantSurname != null) {
        predicateOccupant = builder.in();
    }*/

    //this should be done with criteria, but joins are awfull
    @Override
    public List<Reservation> findAllBetweenDatesOrEmailAndSurname(Calendar startDate, Calendar endDate, String email, String occupantSurname){
        TypedQuery<Reservation> query = null;
        String andDatesClause = "AND ";
        String emailClause = "";
        String occupantClause = "";
        boolean withParameters = false;

        if (startDate != null && endDate != null) {
            withParameters = true;
            andDatesClause = andDatesClause.concat("(res.startDate >= :startDate AND res.endDate <= :endDate)");
        }
        if (email != null && email.length() > 0) {
            withParameters = true;
            emailClause = "AND res.userEmail = :email";
        }
        if (occupantSurname != null && occupantSurname.length() > 0){
            withParameters = true;
            occupantClause = "AND EXISTS (SELECT o FROM Occupant o WHERE o.surname = :surname)";
        }
        if (!withParameters) {
            return em.createQuery("Select r from Reservation r").getResultList();
        }
        Query queryWithNoParameters = em.createQuery("SELECT r FROM Reservation r WHERE " + andDatesClause + emailClause + occupantClause);
        if (startDate != null && endDate != null) {
            query = (TypedQuery<Reservation>) queryWithNoParameters.setParameter("startDate", startDate).setParameter("endDate", endDate);
        }
        if (email != null && email.length()>0) {
            query = query.setParameter("email", email);
        }
        if (occupantSurname != null && occupantSurname.length()>0) {
            query = query.setParameter("surname", occupantSurname);
        }

        return query.getResultList();
    }

    @Override
    public int updateActive(long reservationId, boolean active) {
        Reservation reservation = findById(Math.toIntExact(reservationId)).orElseThrow(EntityNotFoundException::new);
        reservation.setActive(active);
        em.merge(reservation);
        return 1;
    }

    @Override
    public List<Reservation> findActiveReservationsByEmail(String userEmail) {
        return em.createQuery("SELECT r FROM " + getTableName() + " r WHERE r.userEmail = :userEmail AND r.isActive = true", getModelClass())
                .setParameter("userEmail", userEmail)
                .getResultList();
    }

    @Override
    public boolean isRoomFreeOnDate(long roomId, Calendar startDate, Calendar endDate) {
        return em.createQuery("SELECT r FROM Reservation r " +
                "WHERE r.room.id = :roomId AND (:startDate >= r.startDate OR :endDate <= r.endDate)")
                .setParameter("roomId", roomId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate).getResultList().size() == 0;
    }

    @Override
    String getTableName() {
        return "Reservation ";
    }

    @Override
    Class<Reservation> getModelClass() {
        return Reservation.class;
    }
}
