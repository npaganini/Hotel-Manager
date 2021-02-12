package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.daos.ReservationDao;
import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.interfaces.services.MessageSourceExternalizer;
import ar.edu.itba.paw.models.reservation.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityNotFoundException;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    public static final String BUSINESS_EMAIL = "paw.hotel.manager@gmail.com";

    private final JavaMailSender javaMailSender;
    private final ReservationDao reservationDao;
    private final MessageSourceExternalizer messageSourceExternalizer;


    @Autowired
    public EmailServiceImpl(final JavaMailSender javaMailSender, final ReservationDao reservationDao,
                            final MessageSourceExternalizer messageSourceExternalizer) {
        this.javaMailSender = javaMailSender;
        this.reservationDao = reservationDao;
        this.messageSourceExternalizer = messageSourceExternalizer;
    }

    public void sendConfirmationOfReservation(String to, String hash) {
        LOGGER.debug("About to send email notifying the confirmation of reservation to " + to);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String subject = messageSourceExternalizer.getMessage("email.reservationConfirm.subject");
        LOGGER.debug("Got the following message from message source " + subject);
        try {
            helper.setText(getHtmlMessageForReservation(to, hash), true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(BUSINESS_EMAIL);
        } catch (MessagingException e) {
            LOGGER.error(e.toString());
        }
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendUserCreatedEmail(String to, String password) {
        LOGGER.debug("About to send email notifying the creation of user to " + to);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String subject = messageSourceExternalizer.getMessage("email.userCreated.subject");
        LOGGER.debug("Got the following message from message source " + subject);
        try {
            helper.setText(getHtmlMessageForUserCreation(to, password), true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(BUSINESS_EMAIL);
        } catch (MessagingException e) {
            LOGGER.error(e.toString());
        }
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendCheckinEmail(Reservation reservation) {
        LOGGER.debug("About to send email notifying the check-in of reservation to " + reservation.getUserEmail());
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String subject = messageSourceExternalizer.getMessage("email.checkin.subject");
        LOGGER.debug("Got the following message from message source " + subject);
        try {
            helper.setText(getHtmlMessageForCheckin(reservation.getUserEmail(), reservation.getHash()), true);
            helper.setTo(reservation.getUserEmail());
            helper.setSubject(subject);
            helper.setFrom(BUSINESS_EMAIL);
        } catch (MessagingException e) {
            LOGGER.error(e.toString());
        }
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendRateStayEmail(String reservationHash) {
        LOGGER.debug("About to send e-mail asking to rate stay for reservation " + reservationHash);
        String userEmail = reservationDao
                .findReservationByHash(reservationHash.trim())
                .orElseThrow(() -> new EntityNotFoundException("Can't find reservation with"))
                .getUserEmail();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String subject = messageSourceExternalizer.getMessage("email.ratings.subject");
        LOGGER.debug("Got the following message from message source " + subject);
        try {
            helper.setText(createEmailText(reservationHash.trim()), true);
            helper.setTo(userEmail);
            helper.setSubject(subject);
            helper.setFrom(BUSINESS_EMAIL);
        } catch (MessagingException e) {
            LOGGER.error(e.toString());
        }
        javaMailSender.send(mimeMessage);
    }

    private String getHtmlMessageForCheckin(String userEmail, String hash) {
        return "<h3> " + messageSourceExternalizer.getMessage("email.checkin.welcome") + " </h3> <br> " +
                "<p>" + messageSourceExternalizer.getMessage("email.checkin.yourReservationIdIs") + " <b> " +
                hash + "</b>" + messageSourceExternalizer.getMessage("email.checkin.keepItSafe") +
                "<h4>" + messageSourceExternalizer.getMessage("email.checkin.reminder") + " <br> " +
                "<p> <b>" + messageSourceExternalizer.getMessage("email.username") + ":</b> " + userEmail +
                " <br> <b>" + messageSourceExternalizer.getMessage("email.password") + "</b>: " + userEmail + "</p>";
    }

    private String getHtmlMessageForReservation(String to, String hash) {
        return "<h3> " + messageSourceExternalizer.getMessage("email.reservationConfirm.welcome") + " </h3> <br> "
                + "<h4>" + messageSourceExternalizer.getMessage("email.reservationConfirm.loginInfo") + " "
                + hash + messageSourceExternalizer.getMessage("email.reservationConfirm.info")
                + "<p> <b>" + messageSourceExternalizer.getMessage("email.username") + ":</b> "
                + to + "</p>";
    }

    private String getHtmlMessageForUserCreation(String to, String password) {
        return "<h3> " + messageSourceExternalizer.getMessage("email.userCreated.welcome") + " </h3> <br> " +
                "<h4>" + messageSourceExternalizer.getMessage("email.userCreated.loginInfo") +
                messageSourceExternalizer.getMessage("email.userCreated.info") +
                "<p> <b>" + messageSourceExternalizer.getMessage("email.username") + ":</b> " +
                to + " <br> <b>" + messageSourceExternalizer.getMessage("email.password") + "</b>: " +
                password + "</p>";
    }

    private String createEmailText(String reservation) {
        return getHtmlBeginning() + getHtmlRating(reservation) + getHtmlEnd();
    }

    private String getHtmlBeginning() {
        return "<!DOCTYPE html>\n" +
        "<html>\n" +
            "<head>\n" + "</head>\n" +
            "<body style=\"margin-left: 15px;color:black\">\n" +
                "<div style=\"font-family: Arial\">\n" +
                "    <h1>" + messageSourceExternalizer.getMessage("email.ratings.hopeYouEnjoyed") + "</h1>\n" +
                "</div>\n" +
                "<br>\n" +
                "<div style=\"font-family: Arial\">\n" +
                "    <h2>" + messageSourceExternalizer.getMessage("email.ratings.tellUsWhatYouThought") + "</h2>\n" +
                "</div>\n" +
                "<br><br>\n";
    }

    private String getHtmlRating(String reservation) {
        return getHtmlStars(reservation, messageSourceExternalizer.getMessage("email.ratings.excellent")) +
                getHtmlStars(reservation, messageSourceExternalizer.getMessage("email.ratings.good")) +
                getHtmlStars(reservation, messageSourceExternalizer.getMessage("email.ratings.average")) +
                getHtmlStars(reservation, messageSourceExternalizer.getMessage("email.ratings.bad")) +
                getHtmlStars(reservation, messageSourceExternalizer.getMessage("email.ratings.awful"));
    }

    private String getHtmlStars(String reservation, String stars) {
        return "<div>\n" +
                "    <a href=\"http://pawserver.it.itba.edu.ar/paw-2019b-2/reservations/" + reservation + "/rate?rate=" + stars + "\" target=\"_blank\">\n" +
                "        <button type=\"submit\" class=\"btn btn-lg\">\n" +
                "            <span>5</span>\n" +
                "            <span style=\'color:orange\'>&#9733;</span>\n" +
                "            <span style=\'color:orange\'>&#9733;</span>\n" +
                "            <span style=\'color:orange\'>&#9733;</span>\n" +
                "            <span style=\'color:orange\'>&#9733;</span>\n" +
                "            <span style=\'color:orange\'>&#9733;</span>\n" +
                "        </button>\n" +
                "    </a>\n" +
                "</div>\n" +
                "<br>\n";
    }

    private String getHtmlEnd() {
        return  "<div>\n" +
                    "<h3>" + messageSourceExternalizer.getMessage("email.ratings.thanks") + "</h3>\n" +
                "</div>\n" +
            "</body>\n" +
        "</html>";
    }
}
