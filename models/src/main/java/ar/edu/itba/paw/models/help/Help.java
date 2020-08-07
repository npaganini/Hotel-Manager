package ar.edu.itba.paw.models.help;

import ar.edu.itba.paw.models.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "help")
public class Help {
    public final static String KEY_ID = "id";
    public static final String KEY_HELP_STEP = "help_step";
    public final static String KEY_RESERVATIONID = "reservation_id";
    public final static String KEY_HELP_TEXT = "help_text";

    public final static String NAME = "help";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "NUMERIC(19,0)")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = KEY_HELP_STEP)
    private HelpStep helpStep;

    @Column(name = KEY_HELP_TEXT, nullable = false, length = 140) // tweet length, short brief message
    private String helpText;

    @ManyToOne
    private Reservation reservation;

    public Help(String helpText, Reservation reservation) {
        this.helpText = helpText;
        this.reservation = reservation;
        this.helpStep = HelpStep.UNATTENDED;
    }

    public void setHelpResolved() {
        this.helpStep = HelpStep.RESOLVED;
    }

    public void setHelpStillRequired() {
        this.helpStep = HelpStep.REQUIRES_FURTHER_ACTION;
    }
}
