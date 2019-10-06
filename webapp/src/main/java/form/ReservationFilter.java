package form;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReservationFilter {

    private String startDate;
    private String endDate;
    private String userEmail;
}
