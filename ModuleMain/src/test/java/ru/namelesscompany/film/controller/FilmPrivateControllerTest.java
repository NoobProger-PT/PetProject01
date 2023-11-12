package ru.namelesscompany.film.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.namelesscompany.film.dto.FilmDto;
import ru.namelesscompany.film.service.privateService.PrivateFilmService;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.mpaa.model.Mpaa;
import ru.namelesscompany.security.UserDetailsServ;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FilmPrivateController.class)
@WithMockUser(value = "random name", authorities = "ROLE_OVERLORD")
public class FilmPrivateControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    UserDetailsServ userDetailsServ;
    @MockBean
    private PrivateFilmService filmService;
    private static final FilmDto filmDto = new FilmDto();
    private static final Genre genre = new Genre();
    private static final Mpaa mpaa = new Mpaa();

    @BeforeAll
    public static void createNewFilm() {
        genre.setId(1L);
        genre.setName("Genre");

        mpaa.setId(1L);
        mpaa.setName("Mpaa");

        filmDto.setName("Film name");
        filmDto.setAuthor("Author name");
        filmDto.setCountry("Country");
        filmDto.setReleaseDate(LocalDate.of(2020, 11, 11));
        filmDto.setDuration(120);
        filmDto.setMpaa(mpaa);
        filmDto.setGenres(Set.of(genre));
    }

    @Test
    public void shouldGetById() throws Exception {
        when(filmService.getById(anyLong())).thenReturn(filmDto);
        mockMvc.perform(get("/private/films/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(filmDto.getName())))
                .andExpect(jsonPath("$.author", is(filmDto.getAuthor())))
                .andExpect(jsonPath("$.country", is(filmDto.getCountry())))
                .andExpect(jsonPath("$.duration", is(filmDto.getDuration().intValue())))
                .andExpect(jsonPath("$.mpaa.id", is(filmDto.getMpaa().getId().intValue())))
                .andExpect(jsonPath("$.mpaa.name", is(filmDto.getMpaa().getName())))
                .andExpect(jsonPath("$.genres[0].id", is(genre.getId().intValue())))
                .andExpect(jsonPath("$.genres[0].name", is(genre.getName())));
    }

    @Test
    public void shouldGetByName() throws Exception {
        when(filmService.getByName(anyString())).thenReturn(List.of(filmDto));
        mockMvc.perform(get("/private/films/search?name=fIlM")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(filmDto.getName())))
                .andExpect(jsonPath("$[0].author", is(filmDto.getAuthor())))
                .andExpect(jsonPath("$[0].country", is(filmDto.getCountry())))
                .andExpect(jsonPath("$[0].duration", is(filmDto.getDuration().intValue())))
                .andExpect(jsonPath("$[0].mpaa.id", is(filmDto.getMpaa().getId().intValue())))
                .andExpect(jsonPath("$[0].mpaa.name", is(filmDto.getMpaa().getName())))
                .andExpect(jsonPath("$[0].genres[0].id", is(genre.getId().intValue())))
                .andExpect(jsonPath("$[0].genres[0].name", is(genre.getName())));
    }
}
