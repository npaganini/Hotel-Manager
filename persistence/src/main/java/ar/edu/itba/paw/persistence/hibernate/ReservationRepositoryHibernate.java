package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Reservation> findAllBetweenDatesAndEmail(Calendar startDate, Calendar endDate, String email) {
        final TypedQuery<Reservation> query;
        if (startDate == null && endDate == null && (email == null || email.length() == 0)) return new ArrayList<>();
        else if (email == null || email.length() == 0) {
            query = em.createQuery(
                    "SELECT res FROM Reservation as res WHERE" +
                            " (res.startDate >= :startDate AND res.endDate <= :endDate)",
                    Reservation.class);
        } else if (startDate == null && endDate == null) {
            query = em.createQuery(
                    "SELECT res FROM Reservation as res WHERE" +
                            " res.userEmail = :email",
                    Reservation.class);
        } else {
            query = em.createQuery(
                    "SELECT res FROM Reservation as res WHERE" +
                            " (res.startDate >= :startDate AND res.endDate <= :endDate AND res.userEmail = :email)",
                    Reservation.class);
        }
        if (startDate != null && endDate != null)
            query.setParameter("startDate", startDate).setParameter("endDate", endDate);
        if (email != null && email.length() > 0) query.setParameter("email", email);
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
    public List<Reservation> findActiveReservationByEmail(String userEmail) {
        return em.createQuery("SELECT r FROM " + getTableName() + " r WHERE r.userEmail = :userEmail AND r.isActive = true", getModelClass())
                .setParameter("userEmail", userEmail)
                .getResultList();
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
