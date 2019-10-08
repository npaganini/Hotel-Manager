package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender javaMailSender;

    EmailServiceImpl() {
        this(null);
    }

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendConfirmationOfReservation(String to, String subject, String text) {
        LOGGER.debug("About to send email notifying the confirmation of reservation to " + to);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {
            helper.setText(getHtmlMessageForReservation(to), true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("paw.hotel.manager@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendCheckinEmail(Reservation reservation) {
        LOGGER.debug("About to send email notifying the check-in of reservation to " + reservation.getUserEmail());
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        try {
            helper.setText(getHtmlMessagForCheckin(reservation.getUserEmail(), reservation.getHash()), true);
            helper.setTo(reservation.getUserEmail());
            helper.setSubject("Check-in confirmation");
            helper.setFrom("paw.hotel.manager@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }

    private String getHtmlMessagForCheckin(String userEmail, String hash) {
        return "<h3> Welcome to hotelManager, your check-in had been just done! </h3> <br> " +
                "<p>The identification of your reservation is: <b> " + hash + "</b>. Keep it for any problems you have." +
                "<h4>We remember you that these are your credentials for you to log into the web and manager your minibar. <br> " +
                "<p> <b>username:</b> " + userEmail + " <br> <b>password</b>: " + userEmail + "</p>";
    }

    private String getHtmlMessageForReservation(String to) {
        return "<h3> Welcome to hotelManager, your reservation has been confirmed! </h3> <br> " +
                "<h4>These are your credentials for you to log into the web and manager your minibar. " +
                "<b>Remember that you will see your reservation, once the hotel do the check-in.</b></h4> <br>" +
                "<p> <b>username:</b> " + to + " <br> <b>password</b>: " + to + "</p>";
    }

}
