package ru.namelesscompany.genres.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.genre.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;

    private final Genre genre = new Genre();

    @Test
    public void shouldGetAll() {
        createGenre();
        List<Genre> genres = genreRepository.findAll();
        assertThat(1, equalTo(genres.size()));
        assertThat(genres.get(0).getName(), equalTo(genre.getName()));
    }

    @Test
    public void shouldGetById() {
        createGenre();
        Genre genreById = genreRepository.findById(1L).get();
        assertThat(genreById.getId(), equalTo(genre.getId()));
    }

    @Test
    public void shouldAdd() {
        createGenre();
        assertThat(genreRepository.findById(1L).get().getId(), equalTo(genre.getId()));
    }

    @Test
    public void shouldChange() {
        createGenre();
        Genre change = new Genre();
        change.setId(1L);
        change.setName("Changed genre");
        assertThat(genreRepository.save(change).getName(), equalTo("Changed genre"));
    }

    @Test
    public void shouldDelete() {
        createGenre();
        assertThat(genre.getName(), equalTo(genreRepository.findById(1L).get().getName()));
        genreRepository.deleteById(1L);
        assertThat(Optional.empty(), equalTo(genreRepository.findById(1L)));
    }

    private void createGenre() {
        genre.setId(1L);
        genre.setName("Genre 1");
        genreRepository.save(genre);
    }
}
