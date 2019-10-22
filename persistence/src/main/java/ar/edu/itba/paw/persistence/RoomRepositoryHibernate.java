package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class RoomRepositoryHibernate implements RoomDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<RoomReservationDTO> findAllBetweenDatesAndEmail(String startDate, String endDate, String email) {
        return null;
    }

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
        return null;
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
