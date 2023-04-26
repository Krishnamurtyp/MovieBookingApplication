package com.rbp.movieapp.exception;

public class MoviesNotFound extends RuntimeException {
    public MoviesNotFound(String noMoviesAreAvailable) {
        super(noMoviesAreAvailable);
    }
}
