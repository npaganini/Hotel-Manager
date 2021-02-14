package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.occupant.Occupant;
import ar.edu.itba.paw.models.reservation.Calification;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.*;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepositoryHibernate extends SimpleRepositoryHibernate<Reservation> implements ReservationDao {

    @Override
    public Optional<Reservation> findReservationByHash(String hash) {
        return Optional
                .of(em.createQuery("SELECT r FROM " + getModelName() + " r WHERE r.hash = :hash", getModelClass())
                    .setParameter("hash", hash)
                    .getSingleResult()
        );
    }

    @Override
    public PaginatedDTO<Reservation> getActiveReservations(int page, int pageSize) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cqCount = builder.createQuery(Long.class);
        Root<Reservation> entityRoot = cqCount.from(Reservation.class);
        cqCount.select(builder.count(entityRoot));
        Path<Boolean> active = entityRoot.get("isActive");
        cqCount.where(builder.and(builder.isTrue(active)));
        long count = em.createQuery(cqCount).getSingleResult();

        List<Reservation> activeReservations = em.createQuery("SELECT r FROM Reservation AS r WHERE r.isActive = true", Reservation.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return new PaginatedDTO<>(activeReservations, (count));
    }

    @Override
    public PaginatedDTO<Reservation> findAllBetweenDatesOrEmailAndSurname(Calendar startDate, Calendar endDate, String email, String occupantSurname, int page, int pageSize) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Reservation> query = builder.createQuery(Reservation.class);
        Root<Reservation> root = query.from(Reservation.class);
        Predicate datePredicate = null;
        Predicate emailPredicate = null;
        Predicate occupantPredicate = null;
        int predicatesCount = 0;

        if (startDate != null && endDate != null) {
            datePredicate = builder.and(builder.greaterThanOrEqualTo(root.get("startDate"), startDate),
                    builder.lessThanOrEqualTo(root.get("endDate"), endDate));
            predicatesCount++;
        }
        if (email != null && email.length() > 0) {
           emailPredicate = builder.equal(root.get("userEmail"), email);
           predicatesCount++;
        }
        if (occupantSurname != null && occupantSurname.length() > 0) {
            List<Occupant> occupantsList = em
                    .createQuery("SELECT o.reservation.id FROM Occupant o WHERE o.surname = :surname", Occupant.class)
                    .setParameter("surname", occupantSurname.toLowerCase())
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
            if (occupantsList.size() > 0) {
                occupantPredicate = builder.in(root.get("id")).value(occupantsList);
                predicatesCount++;
            }
        }

        Predicate[] array = new Predicate[predicatesCount];
        int current = 0;
        if (datePredicate != null) array[current++] = datePredicate;
        if (emailPredicate != null) array[current++] = emailPredicate;
        if (occupantPredicate != null) array[current++] = occupantPredicate;

        long count;
        List<Reservation> reservationsList;
        if (predicatesCount > 0) {
            // todo: validar que se está paginando bien este look-up
            count = getTotalCount(Reservation.class, array);
            reservationsList = em.createQuery(query.select(root).where(builder.and(array))).getResultList();
        } else {
            count = getTotalCount(Reservation.class);
            reservationsList = em.createQuery("SELECT r FROM Reservation r", getModelClass()).getResultList();
        }
        return new PaginatedDTO<>(reservationsList, count);
    }

    @Override
    public boolean updateActive(long reservationId, boolean active) {
        Reservation reservation = findById(reservationId).orElseThrow(EntityNotFoundException::new);
        reservation.setActive(active);
        em.merge(reservation);
        return true;
    }

    @Override
    public PaginatedDTO<Reservation> findActiveReservationsByEmail(String userEmail, int page, int pageSize) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cqCount = builder.createQuery(Long.class);
        Root<Reservation> entityRoot = cqCount.from(Reservation.class);
        cqCount.select(builder.count(entityRoot));
        Predicate wherePredicate1 = builder.equal(entityRoot.get("userEmail"), userEmail);
        Predicate wherePredicate2 = builder.isTrue(entityRoot.get("isActive"));
        cqCount.where(builder.and(new Predicate[]{wherePredicate1, wherePredicate2}));
        long count = em.createQuery(cqCount).getSingleResult();

        List<Reservation> reservations = em.createQuery("SELECT r FROM " + getModelName() + " r WHERE r.userEmail = :userEmail AND r.isActive = true", getModelClass())
                .setParameter("userEmail", userEmail)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return new PaginatedDTO<>(reservations, count);
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
    public double getHotelRating() {
        return em.createQuery("SELECT AVG(r.calification) FROM Reservation r", Double.class).getSingleResult();
    }

    @Override
    public PaginatedDTO<Calification> getAllRatings(int page, int pageSize) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cqCount = builder.createQuery(Long.class);
        Root<Reservation> entityRoot = cqCount.from(Reservation.class);
        cqCount.select(builder.count(entityRoot));
        Predicate wherePredicate = builder.isNotNull(entityRoot.get("calification"));
        cqCount.where(builder.and(wherePredicate));
        long count = em.createQuery(cqCount).getSingleResult();

        List<Calification> cals = em.createQuery(
                "SELECT r.calification FROM Reservation r WHERE r.calification IS NOT NULL", Calification.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return new PaginatedDTO<>(cals, count);
    }

    @Override
    public double getRoomRating(long roomId) {
        return em.createQuery(
                "SELECT AVG(r.calification) FROM Reservation r WHERE r.room.id = :roomId", Double.class)
                .setParameter("roomId", roomId)
                .getSingleResult();
    }

    @Override
    public PaginatedDTO<Calification> getRatingsByRoom(long roomId, int page, int pageSize) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cqCount = builder.createQuery(Long.class);
        Root<Reservation> entityRoot = cqCount.from(Reservation.class);
        cqCount.select(builder.count(entityRoot));
        Predicate wherePredicate1 = builder.equal(entityRoot.get("room").get("id"), roomId);
        Predicate wherePredicate2 = builder.isNotNull(entityRoot.get("calification"));
        cqCount.where(builder.and(new Predicate[]{wherePredicate1, wherePredicate2}));
        long count = em.createQuery(cqCount).getSingleResult();

        List<Calification> cals = em.createQuery(
                "SELECT r.calification FROM Reservation r WHERE r.room.id = :roomId AND r.calification IS NOT NULL",
                Calification.class)
                .setParameter("roomId", roomId)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return new PaginatedDTO<>(cals, count);
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
