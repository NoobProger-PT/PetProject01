package ru.namelesscompany.film.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.namelesscompany.film.model.Film;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.genre.repository.GenreRepository;
import ru.namelesscompany.mpaa.model.Mpaa;
import ru.namelesscompany.mpaa.repository.MpaaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FilmRepositoryTest {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MpaaRepository mpaaRepository;
    private final Film film = new Film();
    private static final Genre genre = new Genre();
    private static final Mpaa mpaa = new Mpaa();

    @Test
    public void shouldGetAll() {
        createFilm();
        List<Film> films = filmRepository.findAll();
        assertThat(films.size(), equalTo(1));
        assertThat(films.get(0).getName(), equalTo(film.getName()));
    }

    @Test
    public void shouldGetById() {
        createFilm();
        Film filmById = filmRepository.findById(1L).get();
        assertThat(filmById.getName(), equalTo(film.getName()));
    }

    @Test
    public void shouldGetByIds() {
        createFilm();
        assertThat(1, equalTo(filmRepository.findAllByIdIn(List.of(1L)).size()));
    }

    @Test
    public void shouldSave() {
        createFilm();
        assertThat(filmRepository.findById(1L).get().getId(), equalTo(film.getId()));
    }

    @Test
    public void shouldDelete() {
        createFilm();
        assertThat(film.getId(), equalTo(filmRepository.findById(1L).get().getId()));
        filmRepository.deleteById(1L);
        assertThat(Optional.empty(), equalTo(filmRepository.findById(1L)));
    }

    @Test
    public void shouldGetByName() {
        createFilm();
        List<Film> filmsByName = filmRepository.findAllByNameContainingIgnoreCase("FiLM");
        assertThat(filmsByName.size(), equalTo(1));
        assertThat(filmsByName.get(0).getName(), equalTo(film.getName()));
    }

    private void createFilm() {
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

        genreRepository.save(genre);
        mpaaRepository.save(mpaa);
        filmRepository.save(film);
    }
}
