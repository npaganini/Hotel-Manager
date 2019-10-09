package ar.edu.itba.paw.interfaces.exceptions;

public class RequestInvalidException extends Exception {
    public RequestInvalidException() {
        super("We can't process your request, try again in a few minutes :)");
    }
}
