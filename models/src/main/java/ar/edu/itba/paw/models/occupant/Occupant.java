package ar.edu.itba.paw.models.occupant;

import ar.edu.itba.paw.models.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Occupant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "NUMERIC(19,0)")
    private long id;

    private String name;
    private String surname;

    @ManyToOne
    private Reservation reservation;

    public Occupant(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Occupant)) return false;
        return id == ((Occupant) object).getId();
    }
}
