package ar.edu.itba.paw.interfaces.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OccupantResponse {
    private String firstName;
    private String lastName;
}
