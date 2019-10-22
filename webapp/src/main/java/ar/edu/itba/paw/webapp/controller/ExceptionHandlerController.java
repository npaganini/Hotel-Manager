package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handlerDBError(HttpServletRequest req, Exception ex) {
        return new ModelAndView("500"); // TODO change to 5xx
    }

    @ExceptionHandler(RequestInvalidException.class)
    public ModelAndView handlerRequestParamsError(HttpServletRequest req, Exception ex) {
        return new ModelAndView("400"); // TODO change to 4xx
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerGenericError(HttpServletRequest req, Exception ex) {
        return new ModelAndView("500"); // TODO change to 5xx
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handle(HttpServletRequest req, Exception ex) {
        return new ModelAndView("404");
    }

}
