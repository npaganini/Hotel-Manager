package ar.edu.itba.paw.interfaces.exceptions;

import lombok.Getter;

@Getter
public class UnsupportedAuthorizationMethodException extends Exception {
    public UnsupportedAuthorizationMethodException() {
        super("The authorization method used is not supported.");
    }
}
