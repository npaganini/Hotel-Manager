package ar.edu.itba.paw.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@XmlRootElement
public class RatingDTO {
    private double rating;
}
