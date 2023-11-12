package ru.namelesscompany.genre;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.namelesscompany.genre.dto.FullGenreDto;
import ru.namelesscompany.genre.dto.GenreDto;
import ru.namelesscompany.genre.dto.NewGenreDto;

import static org.assertj.core.api.Assertions.*;

@JsonTest
public class GenreJsonTest {
    @Autowired
    JacksonTester<GenreDto> genreDtoJacksonTester;
    @Autowired
    JacksonTester<FullGenreDto> fullGenreDtoJacksonTester;
    @Autowired
    JacksonTester<NewGenreDto> newGenreDtoJacksonTester;

    @Test
    public void shouldReturnCorrectGenreDtoJson() throws Exception {
        GenreDto genreDto = new GenreDto();
        genreDto.setName("Genre");

        JsonContent<GenreDto> result = genreDtoJacksonTester.write(genreDto);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Genre");
    }

    @Test
    public void shouldReturnCorrectFullGenreDtoJson() throws Exception {
        FullGenreDto fullGenreDto = new FullGenreDto();
        fullGenreDto.setId(1L);
        fullGenreDto.setName("Genre");

        JsonContent<FullGenreDto> result = fullGenreDtoJacksonTester.write(fullGenreDto);
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Genre");
    }

    @Test
    public void shouldReturnCorrectNewGenreDtoJson() throws Exception {
        NewGenreDto newGenreDto = new NewGenreDto();
        newGenreDto.setName("Genre");

        JsonContent<NewGenreDto> result = newGenreDtoJacksonTester.write(newGenreDto);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Genre");
    }
}
