package ar.edu.itba.paw.interfaces.services;

public interface EmailService {

    void sendConfirmationOfReservation(String to, String subject, String text);
    
}
