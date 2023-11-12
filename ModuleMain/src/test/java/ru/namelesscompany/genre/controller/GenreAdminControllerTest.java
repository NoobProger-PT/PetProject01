package ru.namelesscompany.genre.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.namelesscompany.genre.dto.FullGenreDto;
import ru.namelesscompany.genre.dto.NewGenreDto;
import ru.namelesscompany.genre.service.adminService.AdminGenreService;
import ru.namelesscompany.security.UserDetailsServ;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GenreAdminController.class)
@WithMockUser(value = "random name", authorities = "ROLE_OVERLORD")
public class GenreAdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    UserDetailsServ userDetailsServ;
    @MockBean
    private AdminGenreService genreService;

    private static final FullGenreDto fullGenreDto= new FullGenreDto();

    @BeforeAll
    public static void createNewGenre() {
        fullGenreDto.setId(1L);
        fullGenreDto.setName("Genre 1");
    }

    @Test
    public void shouldAddGenre() throws Exception {
        when(genreService.add(any(NewGenreDto.class))).thenReturn(fullGenreDto);
        mockMvc.perform(post("/admin/genre")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(mapper.writeValueAsString(fullGenreDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(fullGenreDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(fullGenreDto.getName())));
    }

    @Test
    public void shouldGetGenreById() throws Exception {
        when(genreService.getById(anyLong())).thenReturn(fullGenreDto);
        mockMvc.perform(get("/admin/genre/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(fullGenreDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(fullGenreDto.getName())));
    }

    @Test
    public void shouldGetAllGenres() throws Exception {
        when(genreService.getAll()).thenReturn(List.of(fullGenreDto));
        mockMvc.perform(get("/admin/genre/all")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(fullGenreDto.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(fullGenreDto.getName())));
    }

    @Test
    public void shouldChangeGenre() throws Exception {
        when(genreService.change(any(NewGenreDto.class), anyLong())).thenReturn(fullGenreDto);
        mockMvc.perform(patch("/admin/genre/1")
                .characterEncoding(StandardCharsets.UTF_8)
                .content(mapper.writeValueAsString(fullGenreDto))
                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(fullGenreDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(fullGenreDto.getName())));
    }

    @Test
    public void shouldDeleteGenre() throws Exception {
        mockMvc.perform(delete("/admin/genre/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
