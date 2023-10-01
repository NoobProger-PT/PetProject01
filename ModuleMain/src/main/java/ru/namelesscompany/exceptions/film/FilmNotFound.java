package ru.namelesscompany.exceptions.film;

public class FilmNotFound extends RuntimeException {
    public FilmNotFound(String s) {
        super(s);
    }
}
