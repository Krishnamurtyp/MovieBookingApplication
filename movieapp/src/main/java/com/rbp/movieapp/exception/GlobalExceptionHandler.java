package com.rbp.movieapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception {

    @ExceptionHandler(MoviesNotFound.class)
    public ResponseEntity<String> incaseOfMoviesNotFound(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SeatAlreadyBooked.class)
    public ResponseEntity<String> incaseOfSeatsAlreadyBooked(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
