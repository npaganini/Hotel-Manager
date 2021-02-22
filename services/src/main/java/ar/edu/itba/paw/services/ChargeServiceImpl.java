package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ChargeDao;
import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.interfaces.dtos.ChargeDeliveryResponse;
import ar.edu.itba.paw.interfaces.dtos.ChargesByUserResponse;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.ChargeService;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ChargeServiceImpl implements ChargeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChargeServiceImpl.class);

    private final ReservationDao reservationDao;
    private final ChargeDao chargeDao;
    private final RoomDao roomDao;

    @Autowired
    public ChargeServiceImpl(ReservationDao reservationDao, ChargeDao chargeDao, RoomDao roomDao) {
        this.reservationDao = reservationDao;
        this.chargeDao = chargeDao;
        this.roomDao = roomDao;
    }

    @Override
    public List<ChargesByUserResponse> checkProductsPurchasedInCheckOut(String reservationHash) throws EntityNotFoundException {
        long reservationId = reservationDao.findReservationByHash(reservationHash)
                .orElseThrow(() -> new EntityNotFoundException("Cant find reservation with hash " + reservationHash))
                .getId();
        Map<Product, Integer> productToQtyMap = chargeDao.getAllChargesInCheckOut(reservationId);
        return productToQtyMap.keySet().stream().map(
                product -> new ChargesByUserResponse(product.getDescription(), product.getId(), product.getPrice(), productToQtyMap.get(product))
        ).collect(Collectors.toList());
    }

    @Override
    public List<Charge> getAllChargesByReservationId(long reservationId) throws RequestInvalidException {
        LOGGER.info("Getting all current charges for reservation with id " + reservationId);
        Optional<Reservation> reservationOptional = reservationDao.findById(reservationId);
        if (!reservationOptional.isPresent() || !reservationOptional.get().isActive()) {
            throw new RequestInvalidException();
        }
        return chargeDao.findChargesByReservationId(reservationId);
    }

    @Override
    public PaginatedDTO<Charge> getAllChargesByReservationId(long reservationId, int page, int pageSize) throws RequestInvalidException {
        if (pageSize < 1 || page < 1) throw new IndexOutOfBoundsException("Pagination requested invalid.");
        LOGGER.info("Getting all current charges for reservation with id " + reservationId);
        Optional<Reservation> reservationOptional = reservationDao.findById(reservationId);
        if (!reservationOptional.isPresent() || !reservationOptional.get().isActive()) {
            throw new RequestInvalidException();
        }
        return chargeDao.findChargesByReservationId(reservationId, page, pageSize);
    }

    @Override
    public double sumCharge(long reservationId) {
        LOGGER.info("Getting the balance of reservation with id" + reservationId);
        return chargeDao.sumCharge(reservationId);
    }

    @Override
    public PaginatedDTO<ChargeDeliveryResponse> getAllChargesNotDelivered(int page, int pageSize) {
        if (pageSize < 1 || page < 1) throw new IndexOutOfBoundsException("Pagination requested invalid.");
        LOGGER.info("Getting all undelivered orders.");
        PaginatedDTO<Charge> charges = chargeDao.findAllChargesNotDelivered(page, pageSize);
        return new PaginatedDTO<>(charges.getList()
                .stream().map(ChargeDeliveryResponse::fromCharge).collect(Collectors.toList()),
                charges.getMaxItems());
    }

    @Override
    @Transactional
    public int setChargeToDelivered(long chargeId) throws RequestInvalidException, EntityNotFoundException {
        Charge charge = chargeDao.findById(chargeId).orElseThrow(() ->
                new EntityNotFoundException("Can't find charge with id " + chargeId));
        if (charge.isDelivered()) {
            throw new RequestInvalidException();
        }
        return chargeDao.updateChargeToDelivered(chargeId);
    }

    @Override
    public int setChargesToDelivered(long roomId) throws RequestInvalidException, EntityNotFoundException {
        Optional<Room> room = roomDao.findById(roomId);
        if (room.isPresent()) {
            List<Charge> chargeList = chargeDao.findChargesByRoomNumber(room.get().getNumber());
            for (Charge c : chargeList) {
                if (c.isDelivered()) {
                    LOGGER.info("Charge with ID: " + c.getId() + " was already delivered.");
                    throw new RequestInvalidException();
                }
            }
            return chargeDao.updateChargesToDelivered(roomId);
        }
        throw new EntityNotFoundException("Can't find room with id " + roomId);
    }
}
