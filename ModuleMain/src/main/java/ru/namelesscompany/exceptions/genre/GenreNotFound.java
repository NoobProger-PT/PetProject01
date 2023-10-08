package ru.namelesscompany.exceptions.genre;

public class GenreNotFound extends RuntimeException {
    public GenreNotFound(String s) {
        super(s);
    }
}
