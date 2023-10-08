package ru.namelesscompany.film.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.namelesscompany.exceptions.film.FilmNotFound;
import ru.namelesscompany.film.dto.NewFilmDto;
import ru.namelesscompany.film.model.Film;
import ru.namelesscompany.film.repository.FilmRepository;
import ru.namelesscompany.film.service.adminService.AdminFilmServiceImpl;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.mpaa.model.Mpaa;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AdminFilmServiceTest {
    private AdminFilmServiceImpl filmService;
    private FilmRepository filmRepository;
    private final Film film = new Film();
    private static final Genre genre = new Genre();
    private static final Mpaa mpaa = new Mpaa();

    @BeforeEach
    public void setUp() {
        filmRepository = mock(FilmRepository.class);
        filmService = new AdminFilmServiceImpl(filmRepository);
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
    public void shouldAddNewFilm() {
        when(filmRepository.save(any())).thenReturn(film);
        var result = filmService.add(new NewFilmDto());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(film.getId(), result.getId());
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
    public void shouldGetById() {
        when(filmRepository.findById(anyLong())).thenReturn(Optional.of(film));
        var result = filmService.getById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(film.getId(), result.getId());
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
    void shouldGetAll() {
        when(filmRepository.findAll()).thenReturn(Collections.singletonList(film));
        var result = filmService.getAll();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(film.getId(), result.get(0).getId());
        Assertions.assertEquals(film.getName(), result.get(0).getName());
        Assertions.assertEquals(film.getAuthor(), result.get(0).getAuthor());
        Assertions.assertEquals(film.getCountry(), result.get(0).getCountry());
        Assertions.assertEquals(film.getReleaseDate(), result.get(0).getReleaseDate());
        Assertions.assertEquals(film.getDuration(), result.get(0).getDuration());
        Assertions.assertEquals(film.getMpaa().getId(), result.get(0).getMpaa().getId());
        Assertions.assertEquals(film.getMpaa().getName(), result.get(0).getMpaa().getName());
        Assertions.assertEquals(film.getGenres().size(), result.get(0).getGenres().size());
    }

    @Test
    void shouldGetByIds() {
        when(filmRepository.findAllByIdIn(anyList())).thenReturn(List.of(film));
        var result = filmService.getByIds(List.of(1L));
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(film.getId(), result.get(0).getId());
        Assertions.assertEquals(film.getName(), result.get(0).getName());
        Assertions.assertEquals(film.getAuthor(), result.get(0).getAuthor());
        Assertions.assertEquals(film.getCountry(), result.get(0).getCountry());
        Assertions.assertEquals(film.getReleaseDate(), result.get(0).getReleaseDate());
        Assertions.assertEquals(film.getDuration(), result.get(0).getDuration());
        Assertions.assertEquals(film.getMpaa().getId(), result.get(0).getMpaa().getId());
        Assertions.assertEquals(film.getMpaa().getName(), result.get(0).getMpaa().getName());
        Assertions.assertEquals(film.getGenres().size(), result.get(0).getGenres().size());
    }

    @Test
    void shouldNotGetByWrongId() {
        Assertions.assertThrows(FilmNotFound.class, () -> {
            filmService.getById(10L);
        });
    }

    @Test
    void shouldUpdateFilm() {
        NewFilmDto newFilmDto = new NewFilmDto();
        film.setName("new Name");

        when(filmRepository.findById(any())).thenReturn(Optional.of(film));
        var result = filmService.update(newFilmDto, 1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(film.getName(), result.getName());
    }

    @Test
    void shouldNotUpdateWithWrongId() {
        NewFilmDto newFilmDto = new NewFilmDto();

        Assertions.assertThrows(FilmNotFound.class, () -> {
            filmService.update(newFilmDto, 10);
        });
    }

    @Test
    void shouldDelete() {
        var filmId = 1L;
        when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));
        Assertions.assertEquals(filmService.delete(filmId), "Фильм удален");
        verify(filmRepository, times(1)).deleteById(any());
    }

    @Test
    void shouldGetByName() {
        when(filmRepository.findAllByNameContainingIgnoreCase(anyString())).thenReturn(List.of(film));
        var result = filmService.getByName("fiLM");
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(film.getId(), result.get(0).getId());
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
