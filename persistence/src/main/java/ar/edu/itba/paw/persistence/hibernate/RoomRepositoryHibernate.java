package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.models.room.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

@Repository
public class RoomRepositoryHibernate extends SimpleRepositoryHibernate<Room> implements RoomDao {

    @Override
    public List<Room> findAllFreeBetweenDates(Calendar startDate, Calendar endDate) {
        final TypedQuery<Room> query = em.createQuery("SELECT ro FROM " + getModelName() + " as ro WHERE NOT EXISTS (" +
                "SELECT res FROM Reservation as res WHERE ((:startDate <= res.endDate " +
                "AND :startDate >= res.startDate) OR (:endDate >= res.startDate AND :endDate <= res.endDate)) AND res.room.id = ro.id)", Room.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    @Override
    public int reserveRoom(long roomId) {
        Room room = findById(roomId).orElseThrow(EntityNotFoundException::new);
        room.setFreeNow(false);
        em.merge(room);
        return 0;
    }

    @Override
    public void freeRoom(long roomId) {
        Room room = findById(roomId).orElseThrow(EntityNotFoundException::new);
        room.setFreeNow(true);
        em.merge(room);
    }

    @Override
    String getModelName() {
        return Room.NAME + " ";
    }

    @Override
    Class<Room> getModelClass() {
        return Room.class;
    }
}
