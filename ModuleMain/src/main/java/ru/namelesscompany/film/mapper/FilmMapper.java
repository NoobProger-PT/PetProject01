package ru.namelesscompany.film.mapper;

import ru.namelesscompany.film.model.Film;
import ru.namelesscompany.film.dto.FilmDto;
import ru.namelesscompany.film.dto.FullFilmDto;
import ru.namelesscompany.film.dto.NewFilmDto;

public class FilmMapper {
    public static Film mapToFilm(NewFilmDto newFilmDto) {
        Film film = new Film();
        film.setName(newFilmDto.getName());
        film.setAuthor(newFilmDto.getAuthor());
        film.setCountry(newFilmDto.getCountry());
        film.setReleaseDate(newFilmDto.getReleaseDate());
        film.setDuration(newFilmDto.getDuration());
        film.setGenres(newFilmDto.getGenres());
        film.setMpaa(newFilmDto.getMpaa());
        return film;
    }

    public static FullFilmDto mapToFullFilmDto(Film film) {
        FullFilmDto fullFilmDto = new FullFilmDto();
        fullFilmDto.setId(film.getId());
        fullFilmDto.setName(film.getName());
        fullFilmDto.setAuthor(film.getAuthor());
        fullFilmDto.setCountry(film.getCountry());
        fullFilmDto.setReleaseDate(film.getReleaseDate());
        fullFilmDto.setDuration(film.getDuration());
        fullFilmDto.setGenres(film.getGenres());
        fullFilmDto.setMpaa(film.getMpaa());
        return fullFilmDto;
    }

    public static FilmDto mapToFilmDto(Film film) {
        FilmDto filmDto = new FilmDto();
        filmDto.setName(film.getName());
        filmDto.setAuthor(film.getAuthor());
        filmDto.setCountry(film.getCountry());
        filmDto.setReleaseDate(film.getReleaseDate());
        filmDto.setDuration(film.getDuration());
        filmDto.setGenres(film.getGenres());
        filmDto.setMpaa(film.getMpaa());
        return filmDto;
    }
}
