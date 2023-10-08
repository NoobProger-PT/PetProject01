package ru.namelesscompany.genre.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.namelesscompany.exceptions.genre.GenreNotFound;
import ru.namelesscompany.genre.dto.NewGenreDto;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.genre.repository.GenreRepository;
import ru.namelesscompany.genre.service.adminService.AdminGenreServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class AdminGenreServiceTest {
    private AdminGenreServiceImpl genreService;
    private GenreRepository genreRepository;
    private final Genre genre = new Genre();

    @BeforeEach
    public void setUp() {
        genreRepository = mock(GenreRepository.class);
        genreService = new AdminGenreServiceImpl(genreRepository);
        when(genreRepository.save(any())).then(invocation -> invocation.getArgument(0));

        genre.setId(1L);
        genre.setName("Genre 1");
    }

    @Test
    public void shouldAddNewGenre() {
        when(genreRepository.save(any())).thenReturn(genre);
        var result = genreService.add(new NewGenreDto());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(genre.getId(), result.getId());
        Assertions.assertEquals(genre.getName(), result.getName());
    }

    @Test
    public void shouldGetById() {
        when(genreRepository.findById(anyLong())).thenReturn(Optional.of(genre));
        var result = genreService.getById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(genre.getId(), result.getId());
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
        Assertions.assertEquals(genre.getId(), result.get(0).getId());
        Assertions.assertEquals(genre.getName(), result.get(0).getName());
    }

    @Test
    public void shouldChange() {
        NewGenreDto newGenreDto = new NewGenreDto();
        newGenreDto.setName("change genre");
        when(genreRepository.findById(anyLong())).thenReturn(Optional.of(genre));
        var result = genreService.change(newGenreDto, 1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(genre.getId(), result.getId());
        Assertions.assertEquals(newGenreDto.getName(), result.getName());
    }

    @Test
    public void shouldNotChangeWithWrongId() {
        NewGenreDto newGenreDto = new NewGenreDto();
        newGenreDto.setName("change genre");
        Assertions.assertThrows(GenreNotFound.class, () -> genreService.change(newGenreDto, 10L));
    }

    @Test
    public void shouldDeleteGenre() {
        when(genreRepository.findById(anyLong())).thenReturn(Optional.of(genre));
        Assertions.assertEquals(genreService.delete(1L), "Жанр удален.");
        verify(genreRepository, times(1)).deleteById(any());
    }

    @Test
    public void shouldNotDeleteByWrongId() {
        Assertions.assertThrows(GenreNotFound.class, () -> genreService.delete(10L));
        verify(genreRepository, times(0)).deleteById(any());
    }
}
