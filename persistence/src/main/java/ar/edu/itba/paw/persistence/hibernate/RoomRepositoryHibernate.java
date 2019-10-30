package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class RoomRepositoryHibernate extends SimpleRepositoryHibernate<Room> implements RoomDao {

    @Override
    public List<RoomReservationDTO> findAllBetweenDatesAndEmail(String startDate, String endDate, String email) {
        if(startDate != null && startDate.length() > 0
            && endDate != null && endDate.length() > 0
            && email != null && email.length() > 0) {
            final TypedQuery<Reservation> query = em.createQuery(
                    "FROM Reservation as res WHERE (res.startDate >= :startDate AND res.endDate <= :endDate AND res.userEmail = :email)", Reservation.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            query.setParameter("email", email);
            final List<Reservation> list = query.getResultList();
            final List<RoomReservationDTO> rrDTO = new LinkedList<>();
            for(Reservation r: list) {
                rrDTO.add(new RoomReservationDTO(r.getRoom(), r));
            }
            return rrDTO;
        }
        return null;
    }

    @Override
    public List<Room> findAllFreeBetweenDates(LocalDate startDate, LocalDate endDate) {
        final TypedQuery<Room> query = em.createQuery("FROM Room as ro WHERE NOT EXISTS (" +
                "FROM Reservation as res WHERE (:startDate <= res.endDate AND :startDate >= res.startDate) OR (:endDate >= res.startDate AND :endDate <= res.endDate) " +
                ")", Room.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    @Override
    public int reserveRoom(long roomId) {
        Room room = findById(roomId).orElseThrow(EntityNotFoundException::new);
        room.setFreeNow(false);
        return 0;
    }

    @Override
    public List<Room> findAllFree() {
        final TypedQuery<Room> query = em.createQuery("FROM Room AS r WHERE r.freeNow = true", Room.class);
        return query.getResultList();
    }

    @Override
    public void freeRoom(long roomId) {
        Room room = findById(roomId).orElseThrow(EntityNotFoundException::new);
        room.setFreeNow(true);
        em.merge(room);
    }

    @Override
    public List<RoomReservationDTO> getRoomsReservedActive() {
        return null;
    }

    @Override
    String getTableName() {
        return Room.TABLE_NAME;
    }

    @Override
    Class<Room> getModelClass() {
        return Room.class;
    }
}
