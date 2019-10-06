package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendConfirmationOfReservation(String to, String subject, String text) {
        LOGGER.debug("About to send email notifying the confirmation of reservation to " + to);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMessage = "<h3> Welcome to hotelManager, your reservation has been confirmed! </h3> <br> " +
                "<h4>These are your credentials for you to log into the web and manager your minibar. " +
                "<b>Remember that you will see your reservation, once the hotel do the check-in.</b></h4> <br>" +
                "<p> <b>username:</b> " + to + " <br> <b>password</b>: " + to + "</p>";

        try {
            helper.setText(htmlMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("paw.hotel.manager@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }

}
