package ru.namelesscompany.genre;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.namelesscompany.genre.dto.FullGenreDto;
import ru.namelesscompany.genre.dto.GenreDto;
import ru.namelesscompany.genre.dto.NewGenreDto;
import ru.namelesscompany.genre.mapper.GenreMapper;
import ru.namelesscompany.genre.model.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenreMapperTest {
    private static final Genre genre = new Genre();
    private static final NewGenreDto newGenreDto = new NewGenreDto();

    @BeforeAll
    public static void createGenre() {
        genre.setId(1L);
        genre.setName("Genre 1");

        newGenreDto.setName("Changed genre 1");
    }

    @Test
    public void mapToGenre() {
        Genre genre1 = GenreMapper.mapToGenre(newGenreDto);
        assertEquals(newGenreDto.getName(), genre1.getName());
    }

    @Test
    public void mapToGenreDto() {
        GenreDto genreDto = GenreMapper.mapToGenreDto(genre);
        assertEquals(genreDto.getName(), genre.getName());
    }

    @Test
    public void mapToFullGenreDto() {
        FullGenreDto fullGenreDto = GenreMapper.mapToFullGenreDto(genre);
        assertEquals(fullGenreDto.getName(), genre.getName());
        assertEquals(fullGenreDto.getId(), genre.getId());
    }
}
