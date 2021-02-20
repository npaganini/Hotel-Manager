package ar.edu.itba.paw.interfaces.dtos;

import ar.edu.itba.paw.models.help.Help;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HelpResponse implements Serializable {
    private long id;
    private String helpStep;
    private String helpText;
    private int roomNumber;

    public static HelpResponse fromHelpRequest(Help helpRequest) {
        final HelpResponse hDto = new HelpResponse();

        hDto.id = helpRequest.getId();
        hDto.helpStep = helpRequest.getHelpStep().toString();
        hDto.helpText = helpRequest.getHelpText();
        hDto.roomNumber = helpRequest.getReservation().getRoom().getNumber();

        return hDto;
    }
}
