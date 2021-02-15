package ar.edu.itba.paw.models.dtos;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class RatingDTO {
    private double rating;
}
