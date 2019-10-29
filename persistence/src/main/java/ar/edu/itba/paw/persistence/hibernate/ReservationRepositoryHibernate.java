package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepositoryHibernate extends SimpleRepositoryHibernate<Reservation> implements ReservationDao {

    @Override
    public List<RoomReservationDTO> findAllReservationsByUserEmail(String userEmail) {
        return null;
    }

    @Override
    public Optional<Reservation> findReservationByHash(String hash) {
        return Optional.empty();
    }

    @Override
    public int updateActive(long reservationId, boolean b) {
        return 0;
    }

    @Override
    public List<RoomReservationDTO> findActiveReservation(String userEmail) {
        return null;
    }

    @Override
    public List<Reservation> getAll() {
        return null;
    }

    @Override
    String getTableName() {
        return Reservation.TABLE_NAME;
    }

    @Override
    Class<Reservation> getModelClass() {
        return Reservation.class;
    }
}
