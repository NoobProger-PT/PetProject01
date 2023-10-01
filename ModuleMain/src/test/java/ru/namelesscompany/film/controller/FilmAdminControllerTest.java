package ru.namelesscompany.film.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.namelesscompany.film.dto.FullFilmDto;
import ru.namelesscompany.film.service.adminService.AdminFilmService;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.mpaa.model.Mpaa;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FilmAdminController.class)
public class FilmAdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    private AdminFilmService filmService;
    private static final FullFilmDto fullFilmDto = new FullFilmDto();
    private static final Genre genre = new Genre();
    private static final Mpaa mpaa = new Mpaa();

    @BeforeAll
    public static void createNewFilm() {
        genre.setId(1L);
        genre.setName("Genre");

        mpaa.setId(1L);
        mpaa.setName("Mpaa");

        fullFilmDto.setId(1L);
        fullFilmDto.setName("Film name");
        fullFilmDto.setAuthor("Author name");
        fullFilmDto.setCountry("Country");
        fullFilmDto.setReleaseDate(LocalDate.of(2020, 11, 11));
        fullFilmDto.setDuration(120);
        fullFilmDto.setMpaa(mpaa);
        fullFilmDto.setGenres(Set.of(genre));
    }

    @Test
    public void shouldAddFilm() throws Exception {
        when(filmService.add(any())).thenReturn(fullFilmDto);
        mockMvc.perform(post("/admin/films")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(mapper.writeValueAsString(fullFilmDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(fullFilmDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(fullFilmDto.getName())))
                .andExpect(jsonPath("$.author", is(fullFilmDto.getAuthor())))
                .andExpect(jsonPath("$.country", is(fullFilmDto.getCountry())))
                .andExpect(jsonPath("$.duration", is(fullFilmDto.getDuration().intValue())))
                .andExpect(jsonPath("$.mpaa.id", is(fullFilmDto.getMpaa().getId().intValue())))
                .andExpect(jsonPath("$.mpaa.name", is(fullFilmDto.getMpaa().getName())))
                .andExpect(jsonPath("$.genres[0].id", is(genre.getId().intValue())))
                .andExpect(jsonPath("$.genres[0].name", is(genre.getName())));
    }

    @Test
    public void shouldUpdate() throws Exception {
        when(filmService.update(any(), anyLong())).thenReturn(fullFilmDto);
        mockMvc.perform(patch("/admin/films/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(mapper.writeValueAsString(fullFilmDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(fullFilmDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(fullFilmDto.getName())))
                .andExpect(jsonPath("$.author", is(fullFilmDto.getAuthor())))
                .andExpect(jsonPath("$.country", is(fullFilmDto.getCountry())))
                .andExpect(jsonPath("$.duration", is(fullFilmDto.getDuration().intValue())))
                .andExpect(jsonPath("$.mpaa.id", is(fullFilmDto.getMpaa().getId().intValue())))
                .andExpect(jsonPath("$.mpaa.name", is(fullFilmDto.getMpaa().getName())))
                .andExpect(jsonPath("$.genres[0].id", is(genre.getId().intValue())))
                .andExpect(jsonPath("$.genres[0].name", is(genre.getName())));
    }

    @Test
    public void shouldGetById() throws Exception {
        when(filmService.getById(anyLong())).thenReturn(fullFilmDto);
        mockMvc.perform(get("/admin/films/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(fullFilmDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(fullFilmDto.getName())))
                .andExpect(jsonPath("$.author", is(fullFilmDto.getAuthor())))
                .andExpect(jsonPath("$.country", is(fullFilmDto.getCountry())))
                .andExpect(jsonPath("$.duration", is(fullFilmDto.getDuration().intValue())))
                .andExpect(jsonPath("$.mpaa.id", is(fullFilmDto.getMpaa().getId().intValue())))
                .andExpect(jsonPath("$.mpaa.name", is(fullFilmDto.getMpaa().getName())))
                .andExpect(jsonPath("$.genres[0].id", is(genre.getId().intValue())))
                .andExpect(jsonPath("$.genres[0].name", is(genre.getName())));
    }

    @Test
    public void shouldGetAll() throws Exception {
        when(filmService.getAll()).thenReturn(List.of(fullFilmDto));
        mockMvc.perform(get("/admin/films/all")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(fullFilmDto.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(fullFilmDto.getName())))
                .andExpect(jsonPath("$[0].author", is(fullFilmDto.getAuthor())))
                .andExpect(jsonPath("$[0].country", is(fullFilmDto.getCountry())))
                .andExpect(jsonPath("$[0].duration", is(fullFilmDto.getDuration().intValue())))
                .andExpect(jsonPath("$[0].mpaa.id", is(fullFilmDto.getMpaa().getId().intValue())))
                .andExpect(jsonPath("$[0].mpaa.name", is(fullFilmDto.getMpaa().getName())))
                .andExpect(jsonPath("$[0].genres[0].id", is(genre.getId().intValue())))
                .andExpect(jsonPath("$[0].genres[0].name", is(genre.getName())));
    }

    @Test
    public void shouldGetByName() throws Exception {
        when(filmService.getByName(anyString())).thenReturn(List.of(fullFilmDto));
        mockMvc.perform(get("/admin/films/search?name=fIlM")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(fullFilmDto.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(fullFilmDto.getName())))
                .andExpect(jsonPath("$[0].author", is(fullFilmDto.getAuthor())))
                .andExpect(jsonPath("$[0].country", is(fullFilmDto.getCountry())))
                .andExpect(jsonPath("$[0].duration", is(fullFilmDto.getDuration().intValue())))
                .andExpect(jsonPath("$[0].mpaa.id", is(fullFilmDto.getMpaa().getId().intValue())))
                .andExpect(jsonPath("$[0].mpaa.name", is(fullFilmDto.getMpaa().getName())))
                .andExpect(jsonPath("$[0].genres[0].id", is(genre.getId().intValue())))
                .andExpect(jsonPath("$[0].genres[0].name", is(genre.getName())));
    }

    @Test
    public void shouldGetByIds() throws Exception {
        when(filmService.getByIds(anyList())).thenReturn(List.of(fullFilmDto));
        mockMvc.perform(get("/admin/films/several")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(fullFilmDto.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(fullFilmDto.getName())))
                .andExpect(jsonPath("$[0].author", is(fullFilmDto.getAuthor())))
                .andExpect(jsonPath("$[0].country", is(fullFilmDto.getCountry())))
                .andExpect(jsonPath("$[0].duration", is(fullFilmDto.getDuration().intValue())))
                .andExpect(jsonPath("$[0].mpaa.id", is(fullFilmDto.getMpaa().getId().intValue())))
                .andExpect(jsonPath("$[0].mpaa.name", is(fullFilmDto.getMpaa().getName())))
                .andExpect(jsonPath("$[0].genres[0].id", is(genre.getId().intValue())))
                .andExpect(jsonPath("$[0].genres[0].name", is(genre.getName())));
    }

    @Test
    public void shouldDelete() throws Exception {
        mockMvc.perform(delete("/admin/films/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
