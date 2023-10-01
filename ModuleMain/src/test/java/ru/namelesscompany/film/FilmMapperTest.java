package ru.namelesscompany.film;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.namelesscompany.film.dto.FilmDto;
import ru.namelesscompany.film.dto.FullFilmDto;
import ru.namelesscompany.film.dto.NewFilmDto;
import ru.namelesscompany.film.mapper.FilmMapper;
import ru.namelesscompany.film.model.Film;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.mpaa.model.Mpaa;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmMapperTest {
    private static final Film film = new Film();
    private static final NewFilmDto newFilmDto = new NewFilmDto();
    private static final Genre genre = new Genre();
    private static final Mpaa mpaa = new Mpaa();

    @BeforeAll
    public static void createFilms() {
        genre.setId(1L);
        genre.setName("Genre");

        mpaa.setId(1L);
        mpaa.setName("Mpaa");

        film.setId(1L);
        film.setName("Film name");
        film.setAuthor("Author name");
        film.setCountry("Country");
        film.setReleaseDate(LocalDate.of(2020, 11, 11));
        film.setDuration(120);
        film.setMpaa(mpaa);
        film.setGenres(Set.of(genre));

        newFilmDto.setName("Film new");
        newFilmDto.setAuthor("Author new");
        newFilmDto.setCountry("Country new");
        newFilmDto.setReleaseDate(LocalDate.of(2020, 11, 11));
        newFilmDto.setDuration(120);
        newFilmDto.setMpaa(mpaa);
        newFilmDto.setGenres(Set.of(genre));
    }

    @Test
    public void mapToFilm() {
        Film film1 = FilmMapper.mapToFilm(newFilmDto);
        assertEquals(newFilmDto.getName(), film1.getName());
        assertEquals(newFilmDto.getAuthor(), film1.getAuthor());
        assertEquals(newFilmDto.getDuration(), film1.getDuration());
        assertEquals(newFilmDto.getCountry(), film1.getCountry());
        assertEquals(newFilmDto.getReleaseDate(), film1.getReleaseDate());
        assertEquals(newFilmDto.getGenres().size(), film1.getGenres().size());
        assertEquals(newFilmDto.getMpaa().getName(), film1.getMpaa().getName());
    }

    @Test
    public void mapToFullFilmDto() {
        FullFilmDto fullFilmDto = FilmMapper.mapToFullFilmDto(film);
        assertEquals(film.getId(), fullFilmDto.getId());
        assertEquals(film.getName(), fullFilmDto.getName());
        assertEquals(film.getAuthor(), fullFilmDto.getAuthor());
        assertEquals(film.getDuration(), fullFilmDto.getDuration());
        assertEquals(film.getCountry(), fullFilmDto.getCountry());
        assertEquals(film.getReleaseDate(), fullFilmDto.getReleaseDate());
        assertEquals(film.getGenres().size(), fullFilmDto.getGenres().size());
        assertEquals(film.getMpaa().getName(), fullFilmDto.getMpaa().getName());
    }

    @Test
    public void mapToFilmDto() {
        FilmDto filmDto = FilmMapper.mapToFilmDto(film);
        assertEquals(film.getName(), filmDto.getName());
        assertEquals(film.getAuthor(), filmDto.getAuthor());
        assertEquals(film.getDuration(), filmDto.getDuration());
        assertEquals(film.getCountry(), filmDto.getCountry());
        assertEquals(film.getReleaseDate(), filmDto.getReleaseDate());
        assertEquals(film.getGenres().size(), filmDto.getGenres().size());
        assertEquals(film.getMpaa().getName(), filmDto.getMpaa().getName());
    }
}
