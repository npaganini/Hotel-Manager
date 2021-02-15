package ar.edu.itba.paw.interfaces.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationConfirmedResponse {
    private long reservationId;
    private String reservationHash;
    private int roomNumber;
}
