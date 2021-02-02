package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTest {
    public static final String INVALID_HASH = "1nV4l1d_h4SH";

    @Mock
    private ReservationDao reservationDao;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Before
    public void init() {
        Mockito.when(reservationDao.findReservationByHash(INVALID_HASH)).thenReturn(Optional.empty());
    }

    @Test(expected = EntityNotFoundException.class)
    public void handleInvalidReservationIdTest() throws EntityNotFoundException {
        reservationService.getReservationByHash(INVALID_HASH);
    }
}
