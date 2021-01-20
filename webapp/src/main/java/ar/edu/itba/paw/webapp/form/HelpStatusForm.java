package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.models.help.HelpStep;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelpStatusForm {
    private long helpId;
    private HelpStep status;
}
