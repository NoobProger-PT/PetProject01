package ru.namelesscompany.mpaa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.namelesscompany.mpaa.dto.FullMpaaDto;
import ru.namelesscompany.mpaa.dto.NewMpaaDto;
import ru.namelesscompany.mpaa.service.adminService.AdminMpaaService;
import ru.namelesscompany.security.UserDetailsServ;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MpaaAdminController.class)
@WithMockUser(value = "random name", authorities = "ROLE_OVERLORD")
public class MpaaAdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    UserDetailsServ userDetailsServ;
    @MockBean
    private AdminMpaaService mpaaService;

    private static final FullMpaaDto fullMpaaDto= new FullMpaaDto();

    @BeforeAll
    public static void createNewMpaa() {
        fullMpaaDto.setId(1L);
        fullMpaaDto.setName("Mpaa 1");
    }

    @Test
    public void shouldAddMpaa() throws Exception {
        when(mpaaService.add(any(NewMpaaDto.class))).thenReturn(fullMpaaDto);
        mockMvc.perform(post("/admin/mpaa")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(mapper.writeValueAsString(fullMpaaDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(fullMpaaDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(fullMpaaDto.getName())));
    }

    @Test
    public void shouldGetMpaaById() throws Exception {
        when(mpaaService.getById(anyLong())).thenReturn(fullMpaaDto);
        mockMvc.perform(get("/admin/mpaa/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(fullMpaaDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(fullMpaaDto.getName())));
    }

    @Test
    public void shouldGetAllMpaas() throws Exception {
        when(mpaaService.getAll()).thenReturn(List.of(fullMpaaDto));
        mockMvc.perform(get("/admin/mpaa/all")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(fullMpaaDto.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(fullMpaaDto.getName())));
    }

    @Test
    public void shouldChangeMpaa() throws Exception {
        when(mpaaService.change(any(NewMpaaDto.class), anyLong())).thenReturn(fullMpaaDto);
        mockMvc.perform(patch("/admin/mpaa/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(mapper.writeValueAsString(fullMpaaDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(fullMpaaDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(fullMpaaDto.getName())));
    }

    @Test
    public void shouldDeleteMpaa() throws Exception {
        mockMvc.perform(delete("/admin/mpaa/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
