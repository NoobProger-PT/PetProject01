package ru.namelesscompany.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.namelesscompany.user.dto.ShortUserDto;
import ru.namelesscompany.user.service.publicService.PublicUserService;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserPublicController.class)
public class UserPublicControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private PublicUserService userService;

    private static final ShortUserDto shortUserDto = new ShortUserDto();

    @BeforeAll
    public static void createNewUser() {
        //Инициализация пользователя № 1
        shortUserDto.setName("Random name 1");
    }

    @Test
    public void shouldGetUserById() throws Exception {
        when(userService.get(anyLong())).thenReturn(shortUserDto);
        mockMvc.perform(get("/public/users/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(shortUserDto.getName())));
    }
}
