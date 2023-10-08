package ru.namelesscompany.genre.mapper;

import ru.namelesscompany.genre.dto.FullGenreDto;
import ru.namelesscompany.genre.dto.GenreDto;
import ru.namelesscompany.genre.dto.NewGenreDto;
import ru.namelesscompany.genre.model.Genre;

public class GenreMapper {
    public static Genre mapToGenre(NewGenreDto newGenreDto) {
        Genre genre = new Genre();
        genre.setName(newGenreDto.getName());
        return genre;
    }

    public static FullGenreDto mapToFullGenreDto(Genre genre) {
        FullGenreDto fullGenreDto = new FullGenreDto();
        fullGenreDto.setId(genre.getId());
        fullGenreDto.setName(genre.getName());
        return fullGenreDto;
    }

    public static GenreDto mapToGenreDto(Genre genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.setName(genre.getName());
        return genreDto;
    }
}
