package ru.namelesscompany.genres.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.namelesscompany.exceptions.genre.GenreNotFound;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.genre.repository.GenreRepository;
import ru.namelesscompany.genre.service.privateService.PrivateGenreServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrivateGenreServiceTest {
    private PrivateGenreServiceImpl genreService;
    private GenreRepository genreRepository;
    private final Genre genre = new Genre();

    @BeforeEach
    public void setUp() {
        genreRepository = mock(GenreRepository.class);
        genreService = new PrivateGenreServiceImpl(genreRepository);
        when(genreRepository.save(any())).then(invocation -> invocation.getArgument(0));

        genre.setId(1L);
        genre.setName("Genre 1");
    }

    @Test
    public void shouldGetById() {
        when(genreRepository.findById(anyLong())).thenReturn(Optional.of(genre));
        var result = genreService.getById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(genre.getName(), result.getName());
    }

    @Test
    public void shouldNotGetById() {
        Assertions.assertThrows(GenreNotFound.class, () -> genreService.getById(10L));
    }

    @Test
    public void shouldGetAll() {
        when(genreRepository.findAll()).thenReturn(List.of(genre));
        var result = genreService.getAll();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(genre.getName(), result.get(0).getName());
    }
}
