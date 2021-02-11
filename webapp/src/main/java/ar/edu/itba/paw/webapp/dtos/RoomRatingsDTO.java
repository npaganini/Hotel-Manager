package ar.edu.itba.paw.webapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoomRatingsDTO {
    private List<String> ratings;
}
