package com.perra.bookshandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RessourceAlreadyExistAdvice {
    @ResponseBody
    @ExceptionHandler(RessourceAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String bookAlreadyExistHandler(RessourceAlreadyExistException ex) {
        return ex.getMessage();
    }
}
