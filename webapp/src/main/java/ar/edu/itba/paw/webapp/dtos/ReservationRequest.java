package ar.edu.itba.paw.webapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReservationRequest {
    private Calendar startDate;
    private Calendar endDate;
    private String userEmail;
    private Long roomId;
}
