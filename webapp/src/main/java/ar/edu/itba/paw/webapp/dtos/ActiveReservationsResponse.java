package ar.edu.itba.paw.webapp.dtos;

import ar.edu.itba.paw.interfaces.dtos.ActiveReservationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActiveReservationsResponse {
    private List<ActiveReservationResponse> activeReservations;
}
