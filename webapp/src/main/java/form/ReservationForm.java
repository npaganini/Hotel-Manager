package form;

import ar.edu.itba.paw.models.charge.Charge;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReservationForm {

    private String startDate;
    private String endDate;
    private List<Charge> extraCharges = new ArrayList<>();
    private String userEmail;
    private long roomId;

}

