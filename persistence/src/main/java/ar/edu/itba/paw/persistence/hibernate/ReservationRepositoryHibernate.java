package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.models.reservation.Calification;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class ReservationRepositoryHibernate extends SimpleRepositoryHibernate<Reservation> implements ReservationDao {

    @Override
    public Optional<Reservation> findReservationByHash(String hash) {
        return Optional.of(
                em.createQuery("SELECT r FROM " + getModelName() + " r WHERE r.hash = :hash", getModelClass())
                        .setParameter("hash", hash)
                        .getSingleResult()
        );
    }

    @Override
    public List<Reservation> findAllBetweenDatesOrEmailAndSurname(Calendar startDate, Calendar endDate, String email, String occupantSurname) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Reservation> query = builder.createQuery(Reservation.class);
        Root<Reservation> root = query.from(Reservation.class);
        Predicate datePredicate = null;
        Predicate emailPredicate = null;
        Predicate occupantPredicate = null;
        int predicatesCount = 0;

        if (startDate != null && endDate != null) {
            datePredicate = builder.and(builder.greaterThanOrEqualTo(root.get("startDate"),
                    startDate), builder.lessThanOrEqualTo(root.get("endDate"), endDate));
            predicatesCount++;
        }
        if (email != null && email.length() > 0) {
           emailPredicate = builder.equal(root.get("userEmail"), email);
           predicatesCount++;
        }
        if (occupantSurname != null && occupantSurname.length() > 0) {
            List occupantsList = em
                    .createQuery("SELECT o.reservation.id FROM Occupant o WHERE o.surname = :surname")
                    .setParameter("surname", occupantSurname.toLowerCase()).getResultList();
            if (occupantsList.size() > 0) {
                occupantPredicate = builder.in(root.get("id"))
                        .value(occupantsList);
                predicatesCount++;
            }
        }

        Predicate[] array = new Predicate[predicatesCount];
        int current=0;
        if (datePredicate != null) array[current++] = datePredicate;
        if (emailPredicate != null) array[current++] = emailPredicate;
        if (occupantPredicate != null) array[current++] = occupantPredicate;

        return predicatesCount > 0 ? em.createQuery(query.select(root)
                .where(builder.and(array)))
                .getResultList() : em.createQuery("SELECT r FROM Reservation r").getResultList();
    }

    @Override
    public boolean updateActive(long reservationId, boolean active) {
        Reservation reservation = findById(reservationId).orElseThrow(EntityNotFoundException::new);
        reservation.setActive(active);
        em.merge(reservation);
        return true;
    }

    @Override
    public List<Reservation> findActiveReservationsByEmail(String userEmail) {
        return em.createQuery("SELECT r FROM " + getModelName() + " r WHERE r.userEmail = :userEmail AND r.isActive = true", getModelClass())
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
    public void rateStay(long id, String rate) {
        Reservation reservation = findById(id).orElseThrow(EntityNotFoundException::new);
        reservation.setCalification(Calification.valueOf(rate));
        em.merge(reservation);
    }

    @Override
    String getModelName() {
        return Reservation.NAME + " ";
    }

    @Override
    Class<Reservation> getModelClass() {
        return Reservation.class;
    }
}
