package ru.namelesscompany.genre.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.namelesscompany.genre.dto.GenreDto;
import ru.namelesscompany.genre.service.privateService.PrivateGenreService;
import ru.namelesscompany.security.UserDetailsServ;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GenrePrivateController.class)
@WithMockUser(value = "random name", authorities = "ROLE_OVERLORD")
public class GenrePrivateControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserDetailsServ userDetailsServ;
    @MockBean
    private PrivateGenreService genreService;

    private static final GenreDto genreDto= new GenreDto();

    @BeforeAll
    public static void createNewGenre() {
        genreDto.setName("Genre 1");
    }

    @Test
    @WithMockUser("random name")
    public void shouldGetGenreById() throws Exception {
        when(genreService.getById(anyLong())).thenReturn(genreDto);
        mockMvc.perform(get("/private/genre/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(genreDto.getName())));
    }

    @Test
    @WithMockUser("random name")
    public void shouldGetAllGenres() throws Exception {
        when(genreService.getAll()).thenReturn(List.of(genreDto));
        mockMvc.perform(get("/private/genre/all")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(genreDto.getName())));
    }
}
