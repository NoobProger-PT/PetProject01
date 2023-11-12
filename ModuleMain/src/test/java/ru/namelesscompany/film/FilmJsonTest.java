package ru.namelesscompany.film;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.namelesscompany.film.dto.FilmDto;
import ru.namelesscompany.film.dto.FullFilmDto;
import ru.namelesscompany.film.dto.NewFilmDto;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.mpaa.model.Mpaa;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@JsonTest
public class FilmJsonTest {
    @Autowired
    JacksonTester<NewFilmDto> newFilmDtoJacksonTester;
    @Autowired
    JacksonTester<FullFilmDto> fullFilmDtoJacksonTester;
    @Autowired
    JacksonTester<FilmDto> filmDtoJacksonTester;
    private static final Genre genre = new Genre();
    private static final Mpaa mpaa = new Mpaa();

    @BeforeEach
    public void createGenreAndMpaa() {
        genre.setId(1L);
        genre.setName("Genre");

        mpaa.setId(1L);
        mpaa.setName("Mpaa");
    }

    @Test
    public void shouldReturnCorrectNewFilmDtoJson() throws Exception {
        NewFilmDto newFilmDto = new NewFilmDto();

        newFilmDto.setName("Film new");
        newFilmDto.setAuthor("Author new");
        newFilmDto.setCountry("Country new");
        newFilmDto.setReleaseDate(LocalDate.of(2022, 11, 11));
        newFilmDto.setDuration(120);
        newFilmDto.setMpaa(mpaa);
        newFilmDto.setGenres(Set.of(genre));

        JsonContent<NewFilmDto> result = newFilmDtoJacksonTester.write(newFilmDto);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Film new");
        assertThat(result).extractingJsonPathStringValue("$.author").isEqualTo("Author new");
        assertThat(result).extractingJsonPathStringValue("$.country").isEqualTo("Country new");
        assertThat(result).extractingJsonPathStringValue("$.releaseDate").isEqualTo("2022-11-11");
        assertThat(result).extractingJsonPathNumberValue("$.duration").isEqualTo(120);
        assertThat(result).extractingJsonPathNumberValue("$.genres[0].id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.genres[0].name").isEqualTo("Genre");
        assertThat(result).extractingJsonPathNumberValue("$.mpaa.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.mpaa.name").isEqualTo("Mpaa");
    }

    @Test
    public void shouldReturnCorrectFullFilmDto() throws Exception {
        FullFilmDto fullFilmDto = new FullFilmDto();

        fullFilmDto.setId(1L);
        fullFilmDto.setName("Film");
        fullFilmDto.setAuthor("Author");
        fullFilmDto.setCountry("Country");
        fullFilmDto.setReleaseDate(LocalDate.of(2022, 11, 11));
        fullFilmDto.setDuration(120);
        fullFilmDto.setMpaa(mpaa);
        fullFilmDto.setGenres(Set.of(genre));

        JsonContent<FullFilmDto> result = fullFilmDtoJacksonTester.write(fullFilmDto);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Film");
        assertThat(result).extractingJsonPathStringValue("$.author").isEqualTo("Author");
        assertThat(result).extractingJsonPathStringValue("$.country").isEqualTo("Country");
        assertThat(result).extractingJsonPathStringValue("$.releaseDate").isEqualTo("2022-11-11");
        assertThat(result).extractingJsonPathNumberValue("$.duration").isEqualTo(120);
        assertThat(result).extractingJsonPathNumberValue("$.genres[0].id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.genres[0].name").isEqualTo("Genre");
        assertThat(result).extractingJsonPathNumberValue("$.mpaa.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.mpaa.name").isEqualTo("Mpaa");
    }

    @Test
    public void shouldReturnCorrectFilmDto() throws Exception {
        FilmDto filmDto = new FilmDto();

        filmDto.setName("Film");
        filmDto.setAuthor("Author");
        filmDto.setCountry("Country");
        filmDto.setReleaseDate(LocalDate.of(2022, 11, 11));
        filmDto.setDuration(120);
        filmDto.setMpaa(mpaa);
        filmDto.setGenres(Set.of(genre));

        JsonContent<FilmDto> result = filmDtoJacksonTester.write(filmDto);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Film");
        assertThat(result).extractingJsonPathStringValue("$.author").isEqualTo("Author");
        assertThat(result).extractingJsonPathStringValue("$.country").isEqualTo("Country");
        assertThat(result).extractingJsonPathStringValue("$.releaseDate").isEqualTo("2022-11-11");
        assertThat(result).extractingJsonPathNumberValue("$.duration").isEqualTo(120);
        assertThat(result).extractingJsonPathNumberValue("$.genres[0].id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.genres[0].name").isEqualTo("Genre");
        assertThat(result).extractingJsonPathNumberValue("$.mpaa.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.mpaa.name").isEqualTo("Mpaa");
    }
}
