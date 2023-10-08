package ru.namelesscompany.film.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.namelesscompany.film.model.Film;
import ru.namelesscompany.film.repository.FilmRepository;
import ru.namelesscompany.film.service.publicService.PublicFilmServiceImpl;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.mpaa.model.Mpaa;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PublicFilmServiceTest {
    private PublicFilmServiceImpl filmService;
    private FilmRepository filmRepository;
    private final Film film = new Film();
    private static final Genre genre = new Genre();
    private static final Mpaa mpaa = new Mpaa();

    @BeforeEach
    public void setUp() {
        filmRepository = mock(FilmRepository.class);
        filmService = new PublicFilmServiceImpl(filmRepository);
        when(filmRepository.save(any())).then(invocation -> invocation.getArgument(0));

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
    }

    @Test
    public void shouldGetById() {
        when(filmRepository.findById(anyLong())).thenReturn(Optional.of(film));
        var result = filmService.getById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(film.getName(), result.getName());
        Assertions.assertEquals(film.getAuthor(), result.getAuthor());
        Assertions.assertEquals(film.getCountry(), result.getCountry());
        Assertions.assertEquals(film.getReleaseDate(), result.getReleaseDate());
        Assertions.assertEquals(film.getDuration(), result.getDuration());
        Assertions.assertEquals(film.getMpaa().getId(), result.getMpaa().getId());
        Assertions.assertEquals(film.getMpaa().getName(), result.getMpaa().getName());
        Assertions.assertEquals(film.getGenres().size(), result.getGenres().size());
    }

    @Test
    void shouldGetByName() {
        when(filmRepository.findAllByNameContainingIgnoreCase(anyString())).thenReturn(List.of(film));
        var result = filmService.getByName("fiLM");
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(film.getName(), result.get(0).getName());
        Assertions.assertEquals(film.getAuthor(), result.get(0).getAuthor());
        Assertions.assertEquals(film.getCountry(), result.get(0).getCountry());
        Assertions.assertEquals(film.getReleaseDate(), result.get(0).getReleaseDate());
        Assertions.assertEquals(film.getDuration(), result.get(0).getDuration());
        Assertions.assertEquals(film.getMpaa().getId(), result.get(0).getMpaa().getId());
        Assertions.assertEquals(film.getMpaa().getName(), result.get(0).getMpaa().getName());
        Assertions.assertEquals(film.getGenres().size(), result.get(0).getGenres().size());
    }
}
