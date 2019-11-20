package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.reservation.Reservation;

public interface EmailService {

    void sendConfirmationOfReservation(String to, String subject, String text);

    void sendCheckinEmail(Reservation reservation);

    void sendRateStayEmail(String reservationHash);
}
