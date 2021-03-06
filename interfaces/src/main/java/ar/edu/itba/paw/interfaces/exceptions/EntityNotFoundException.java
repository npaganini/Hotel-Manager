package ar.edu.itba.paw.interfaces.exceptions;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends Exception {
    private final String description;

    public EntityNotFoundException(String description) {
        super("The entity you were looking for was not found");
        this.description = description;
    }
}
