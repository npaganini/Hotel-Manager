package ar.edu.itba.paw.webapp.dtos;

import ar.edu.itba.paw.interfaces.dtos.CalificationResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoomRatingsDTO {
    private List<CalificationResponse> ratings;
}
