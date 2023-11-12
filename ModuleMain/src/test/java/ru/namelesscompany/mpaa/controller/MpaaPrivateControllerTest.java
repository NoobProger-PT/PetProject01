package ru.namelesscompany.mpaa.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.namelesscompany.mpaa.dto.MpaaDto;
import ru.namelesscompany.mpaa.service.privateService.PrivateMpaaService;
import ru.namelesscompany.security.UserDetailsServ;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MpaaPrivateController.class)
@WithMockUser(value = "random name", authorities = "ROLE_OVERLORD")
public class MpaaPrivateControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserDetailsServ userDetailsServ;
    @MockBean
    private PrivateMpaaService mpaaService;

    private static final MpaaDto mpaaDto = new MpaaDto();

    @BeforeAll
    public static void createNewMpaa() {
        mpaaDto.setName("Mpaa 1");
    }

    @Test
    public void shouldGetMpaaById() throws Exception {
        when(mpaaService.getById(anyLong())).thenReturn(mpaaDto);
        mockMvc.perform(get("/private/mpaa/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(mpaaDto.getName())));
    }

    @Test
    public void shouldGetAllMpaas() throws Exception {
        when(mpaaService.getAll()).thenReturn(List.of(mpaaDto));
        mockMvc.perform(get("/private/mpaa/all")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(mpaaDto.getName())));
    }
}
