package ar.edu.itba.paw.interfaces.exceptions;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends Exception {

    private String description;

    public EntityNotFoundException(String description) {
        super("The entity you were trying to look was not found");
        this.description = description;
    }

}
