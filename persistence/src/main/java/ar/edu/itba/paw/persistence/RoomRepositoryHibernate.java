package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class RoomRepositoryHibernate implements RoomDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<RoomReservationDTO> findAllBetweenDatesAndEmail(String startDate, String endDate, String email) {
        if(startDate != null && startDate.length() > 0
            && endDate != null && endDate.length() > 0
            && email != null && email.length() > 0) {
//            final TypedQuery<Room> query = em.createQuery("FROM Room AS r " +
//                    "WHERE ")
            return new LinkedList<>();
        }
        return null;
    }

//    final TypedQuery<User> query = em.createQuery("from User as u where u.username = :username", User.class);
//        query.setParameter("username", username);
//    final List<User> list = query.getResultList();
//        return Optional.ofNullable(list.get(0));

    @Override
    public List<Room> findAllFreeBetweenDates(String startDate, String endDate) {
        return null;
    }

    @Override
    public int reservateRoom(long roomId) {
        return 0;
    }

    @Override
    public List<Room> findAllFree() {
        final TypedQuery<Room> query = em.createQuery("FROM Room AS r WHERE r.freeNow = true", Room.class);
        return query.getResultList();
    }

    @Override
    public void freeRoom(long roomId) {

    }

    @Override
    public List<RoomReservationDTO> getRoomsReservedActive() {
        return null;
    }

    @Override
    public Room save(Room room) {
        final Room roomToAdd = new Room(room.getId(), room.getRoomType(), room.isFreeNow(), room.getNumber(), room.getMyReservations());
        em.persist(roomToAdd);
        return roomToAdd;
    }

    @Override
    public Optional<Room> findById(long id) {
        return Optional.ofNullable(em.find(Room.class, id));
    }

    @Override
    public List<Room> findAll() {
        return em.createQuery("from Room", Room.class).getResultList();
    }
}
