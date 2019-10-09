package ar.edu.itba.paw.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {
    @InjectMocks
    private EmailServiceImpl emailService;

    /**
     * @function_to_test sendConfirmationOfReservation(String to, String subject, String text)      FUNCTION RETURNS VOID
     * uses javaMailSender.send(MimeMessage mimeMessage)                                            RETURNS VOID
     **/
    @Test
    public void testSendConfirmationOfReservation() {}

    /**
     * @function_to_test sendCheckinEmail(Reservation reservation)      FUNCTION RETURNS VOID
     * uses javaMailSender.send(MimeMessage mimeMessage)                RETURNS VOID
     **/
    @Test
    public void testSendCheckinEmail() {}
}
