package ru.namelesscompany.users.controller.privat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.namelesscompany.user.controller.UserPrivateController;
import ru.namelesscompany.user.dto.NewUserDto;
import ru.namelesscompany.user.dto.UserDto;
import ru.namelesscompany.user.service.privateService.PrivateUserService;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserPrivateController.class)
public class UserPrivateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private PrivateUserService userService;

    private static final UserDto userDto1 = new UserDto();

    @BeforeAll
    public static void createNewUser() {
        //Инициализация пользователя № 1
        userDto1.setName("Random name 1");
        userDto1.setRegistrationDate(LocalDate.of(2000,2,12));
        userDto1.setEmail("soap1@mail.exp");
    }

    @Test
    public void shouldGetUserById() throws Exception {
        when(userService.get(anyLong())).thenReturn(userDto1);
        mockMvc.perform(get("/private/users/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(userDto1.getName())))
                .andExpect(jsonPath("$.registrationDate", is(userDto1.getRegistrationDate().toString())))
                .andExpect(jsonPath("$.email", is(userDto1.getEmail())));
    }

    @Test
    public void shouldAddUser() throws Exception {
        when(userService.add(any(NewUserDto.class))).thenReturn(userDto1);
        mockMvc.perform(post("/private/users")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(mapper.writeValueAsString(userDto1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(userDto1.getName())))
                .andExpect(jsonPath("$.registrationDate", is(userDto1.getRegistrationDate().toString())))
                .andExpect(jsonPath("$.email", is(userDto1.getEmail())));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        mockMvc.perform(delete("/private/users/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldPatchUser() throws Exception {
        when(userService.update(any(NewUserDto.class), anyLong())).thenReturn(userDto1);
        mockMvc.perform(patch("/private/users/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(mapper.writeValueAsString(userDto1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(userDto1.getName())))
                .andExpect(jsonPath("$.registrationDate", is(userDto1.getRegistrationDate().toString())))
                .andExpect(jsonPath("$.email", is(userDto1.getEmail())));
    }
}