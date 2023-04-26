package com.rbp.movieapp.exception;

public class SeatAlreadyBooked extends RuntimeException {
    public SeatAlreadyBooked(String s) {
        super(s);
    }
}
