package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.daos.RoomDao;
import ar.edu.itba.paw.interfaces.daos.UserDao;
import ar.edu.itba.paw.models.dtos.RoomReservationDTO;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.room.RoomType;
import ar.edu.itba.paw.models.user.User;
import ar.edu.itba.paw.models.user.UserRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceImplTest {
    private static final long ID_1 = 1L;
    private static final long ID_2 = 2L;
    private static final double TOTAL = 45.59;
    private static final int DELTA_DIFFERENCE = 0;
    private static final String FAKE_VALID_EMAIL = "email@email.com";
    private static final String SUBJECT = "subject";
    private static final String MESSAGE = "message";
    private static final String PRODUCT_NAME_1 = "Coca-Cola";
    private static final float PRODUCT_PRICE_1 = 15.99f;
    private static final String START_DATE = "2019-09-30";
    private static final String END_DATE = "2019-10-10";
    private static final int ROOM_NUMBER_1 = 101;
    private static final int ROOM_NUMBER_2 = 102;
    private static final boolean FALSE = false;
    private static final boolean TRUE = true;
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";
    private static final int BOOLEAN_INT_TRUE = 1;

    @Mock
    private RoomDao roomDao;
    @Mock
    private UserDao userDao;
    @Mock
    private ReservationDao reservationDao;
    @Mock
    private EmailServiceImpl emailService;

    @InjectMocks
    private RoomServiceImpl roomService;


    /**
     * @function_to_test List<Room> getRoomsList()
     * uses roomDao.findAllFree()
     **/
    @Test
    public void testGetRoomsList() {
        // 1. Setup!
        Room room1 = new Room(ID_1, RoomType.DOUBLE, TRUE, ROOM_NUMBER_1);
        Room room2 = new Room(ID_2, RoomType.TRIPLE, TRUE, ROOM_NUMBER_2);
        List<Room> roomsList = new LinkedList<>();
        roomsList.add(room1);
        roomsList.add(room2);
        Mockito.when(roomDao.findAllFree()).thenReturn(roomsList);
        // 2. SUT
        List<Room> freeRoomsList = roomService.getRoomsList();
        // 3. Asserts
        Assert.assertNotNull(freeRoomsList);
        Assert.assertNotNull(freeRoomsList.get(0));
        Assert.assertNotNull(freeRoomsList.get(1));
        // list ordered by room number ASC
        Assert.assertEquals(ROOM_NUMBER_1, freeRoomsList.get(0).getNumber());
        Assert.assertEquals(ROOM_NUMBER_2, freeRoomsList.get(1).getNumber());
    }

    /**
     * @function_to_test Room getRoom(long roomID)
     * uses roomDao.findById(long roomID).orElse(null)
     **/
    @Test
    public void testGetRoom() {
        // 1. Setup!
        Mockito.when(roomDao.findById(ID_1)).thenReturn(java.util.Optional.of(new Room(ID_1, RoomType.DOUBLE, TRUE, ROOM_NUMBER_1)));
        // 2. SUT
        Room myRoom = roomService.getRoom(ID_1);
        // 3. Asserts
        Assert.assertNotNull(myRoom);
        Assert.assertEquals(ID_1, myRoom.getId());
        Assert.assertEquals(ROOM_NUMBER_1, myRoom.getNumber());
    }

    /**
     * @function_to_test void doReservation(Reservation reserva)
     * uses userDao.findByEmail(String email)
     * uses userDao.save(User newUser)                                                                  UNNECESSARY
     * uses reservationDao.save(Reservation newReservation)
     * uses emailService.sendConfirmationOfReservation(String email, String subject, String message)    RETURNS VOID
     **/
    @Test
    public void testDoReservation() {
        // 1. Setup!
        User userToUse = new User(ID_1, FAKE_VALID_EMAIL, PASSWORD, USERNAME, UserRole.CLIENT);
        Reservation reservationToUse = new Reservation(ID_1, FAKE_VALID_EMAIL, Date.valueOf(START_DATE).toLocalDate(), Date.valueOf(END_DATE).toLocalDate(), ID_1);
        Mockito.when(userDao.findByEmail(FAKE_VALID_EMAIL)).thenReturn(userToUse);
//        Mockito.when(userDao.save(userToUse)).thenReturn(userToUse);      UNNECESSARY
        Mockito.when(reservationDao.save(reservationToUse)).thenReturn(reservationToUse);
//        Mockito.when(emailService.sendConfirmationOfReservation(FAKE_VALID_EMAIL, SUBJECT, MESSAGE)).thenReturn(void);
        // 2. SUT
        roomService.doReservation(reservationToUse);
        // 3. Asserts
//        Assert.
    }

    /**
     * @function_to_test void reservateRoom(long roomID, Reservation reservation)
     * uses roomDao.reservateRoom(long roomID)
     * uses emailService.sendCheckinEmail(Reservation reservation)                  RETURNS VOID
     **/
    @Test
    public void testReservateRoom() {
        // 1. Setup!
        Reservation reservationToUse = new Reservation(ID_1, FAKE_VALID_EMAIL, Date.valueOf(START_DATE).toLocalDate(), Date.valueOf(END_DATE).toLocalDate(), ID_1);
        Mockito.when(roomDao.reservateRoom(ID_1)).thenReturn(BOOLEAN_INT_TRUE);
        // 2. SUT
        roomService.reservateRoom(ID_1, reservationToUse);
        // 3. Asserts
//        Assert.
    }

    /**
     * @function_to_test void freeRoom(long roomId)
     * uses roomDao.freeRoom(long roomId) RETURNS VOID
     **/
    @Test
    public void testFreeRoom() {
        // 1. Setup!
        // 2. SUT
        roomService.freeRoom(ID_1);
        // 3. Asserts
//        Assert.
    }

    /**
     * @function_to_test List<RoomReservationDTO> findAllBetweenDatesAndEmail(String startDate, String endDate, String email)
     * uses roomDao.findAllBetweenDatesAndEmail(startDate, endDate, email)
     **/
    @Test
    public void testFindAllBetweenDatesAndEmail() {
        // 1. Setup!
        Room room = new Room(ID_1, RoomType.DOUBLE, TRUE, ROOM_NUMBER_1);
        Reservation reservationToUse = new Reservation(ID_1, FAKE_VALID_EMAIL, Date.valueOf(START_DATE).toLocalDate(), Date.valueOf(END_DATE).toLocalDate(), ID_1);
        RoomReservationDTO rrDTO = new RoomReservationDTO(room, reservationToUse);
        List<RoomReservationDTO> listOfFreeRooms = new LinkedList<>();
        listOfFreeRooms.add(rrDTO);
        Mockito.when(roomDao.findAllBetweenDatesAndEmail(START_DATE, END_DATE, FAKE_VALID_EMAIL)).thenReturn(listOfFreeRooms);
        // 2. SUT
        List<RoomReservationDTO> freeRooms = roomService.findAllBetweenDatesAndEmail(START_DATE, END_DATE, FAKE_VALID_EMAIL);
        // 3. Asserts
        Assert.assertNotNull(freeRooms);
        Assert.assertNotNull(freeRooms.get(0));
        Assert.assertEquals(FAKE_VALID_EMAIL, freeRooms.get(0).getReservation().getUserEmail());
        Assert.assertEquals(START_DATE, freeRooms.get(0).getReservation().getStartDate().toString());
        Assert.assertEquals(END_DATE, freeRooms.get(0).getReservation().getEndDate().toString());
    }

    /**
     * @function_to_test List<RoomReservationDTO> findAllFreeBetweenDates(LocalDate startDate, LocalDate endDate)
     * uses roomDao.findAllFreeBetweenDates(startDate, endDate)
     **/
    @Test
    public void testFindAllFreeBetweenDates() {
        // 1. Setup!
        Room room = new Room(ID_1, RoomType.DOUBLE, TRUE, ROOM_NUMBER_1);
        Reservation reservationToUse = new Reservation(ID_1, FAKE_VALID_EMAIL, Date.valueOf(START_DATE).toLocalDate(), Date.valueOf(END_DATE).toLocalDate(), ID_1);
        RoomReservationDTO rrDTO = new RoomReservationDTO(room, reservationToUse);
        List<RoomReservationDTO> listOfFreeRooms = new LinkedList<>();
        listOfFreeRooms.add(rrDTO);
        Mockito.when(roomDao.findAllFreeBetweenDates(Date.valueOf(START_DATE).toLocalDate(), Date.valueOf(END_DATE).toLocalDate())).thenReturn(listOfFreeRooms);
        // 2. SUT
        List<RoomReservationDTO> freeRooms = roomService.findAllFreeBetweenDates(Date.valueOf(START_DATE).toLocalDate(), Date.valueOf(END_DATE).toLocalDate());
        // 3. Asserts
        Assert.assertNotNull(freeRooms);
        Assert.assertNotNull(freeRooms.get(0));
        Assert.assertEquals(START_DATE, freeRooms.get(0).getReservation().getStartDate().toString());
        Assert.assertEquals(END_DATE, freeRooms.get(0).getReservation().getEndDate().toString());
    }
}
