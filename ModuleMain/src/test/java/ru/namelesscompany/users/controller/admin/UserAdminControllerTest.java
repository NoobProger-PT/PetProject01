package ru.namelesscompany.users.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.namelesscompany.user.controller.UserAdminController;
import ru.namelesscompany.user.dto.FullUserDto;
import ru.namelesscompany.user.dto.NewUserDto;
import ru.namelesscompany.user.service.adminService.AdminUserService;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserAdminController.class)
public class UserAdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private AdminUserService userService;

    private static final FullUserDto fullUserDto2 = new FullUserDto();
    private static final FullUserDto fullUserDto1 = new FullUserDto();

    @BeforeAll
    public static void createNewUser() {
        //Инициализация пользователя № 1
        fullUserDto1.setId(1L);
        fullUserDto1.setName("Random name 1");
        fullUserDto1.setRegistrationDate(LocalDate.of(2000,2,12));
        fullUserDto1.setEmail("soap1@mail.exp");
        //Инициализация пользователя № 2
        fullUserDto2.setId(2L);
        fullUserDto2.setName("Random name 2");
        fullUserDto2.setRegistrationDate(LocalDate.of(2010,12,30));
        fullUserDto2.setEmail("soap2@mail.exp");
    }

    @Test
    public void shouldAddUserByAdmin() throws Exception {
        when(userService.add(any(NewUserDto.class))).thenReturn(fullUserDto1);
        mockMvc.perform(post("/admin/users")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(mapper.writeValueAsString(fullUserDto1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(fullUserDto1.getId().intValue())))
                .andExpect(jsonPath("$.name", is(fullUserDto1.getName())))
                .andExpect(jsonPath("$.registrationDate", is(fullUserDto1.getRegistrationDate().toString())))
                .andExpect(jsonPath("$.email", is(fullUserDto1.getEmail())));
    }

    @Test
    public void shouldGetUserById() throws Exception {
        when(userService.getById(anyLong())).thenReturn(fullUserDto1);
        mockMvc.perform(get("/admin/users/1")
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(fullUserDto1.getId().intValue())))
                .andExpect(jsonPath("$.name", is(fullUserDto1.getName())))
                .andExpect(jsonPath("$.registrationDate", is(fullUserDto1.getRegistrationDate().toString())))
                .andExpect(jsonPath("$.email", is(fullUserDto1.getEmail())));
    }

    @Test
    public void shouldGetAllUsers() throws Exception {
        when(userService.getAll()).thenReturn(List.of(fullUserDto1, fullUserDto2));
        mockMvc.perform(get("/admin/users/all")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(fullUserDto1.getId().intValue())))
                .andExpect(jsonPath("$[1].id", is(fullUserDto2.getId().intValue())))
                .andExpect(jsonPath("$[0].email", is(fullUserDto1.getEmail())))
                .andExpect(jsonPath("$[1].email", is(fullUserDto2.getEmail())));
    }

    @Test
    public void shouldGetUsersByIds() throws Exception {
        when(userService.getByIds(anyList())).thenReturn(List.of(fullUserDto1, fullUserDto2));
        mockMvc.perform(get("/admin/users/several")
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(fullUserDto1.getId().intValue())))
                .andExpect(jsonPath("$[1].id", is(fullUserDto2.getId().intValue())))
                .andExpect(jsonPath("$[0].email", is(fullUserDto1.getEmail())))
                .andExpect(jsonPath("$[1].email", is(fullUserDto2.getEmail())));
    }

    @Test
    public void shouldPatchUserByAdmin() throws Exception {
        when(userService.update(any(NewUserDto.class), anyLong())).thenReturn(fullUserDto1);
        mockMvc.perform(patch("/admin/users/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(mapper.writeValueAsString(fullUserDto1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(fullUserDto1.getId().intValue())))
                .andExpect(jsonPath("$.name", is(fullUserDto1.getName())))
                .andExpect(jsonPath("$.registrationDate", is(fullUserDto1.getRegistrationDate().toString())))
                .andExpect(jsonPath("$.email", is(fullUserDto1.getEmail())));
    }

    @Test
    public void shouldDeleteUserByAdmin() throws Exception {
        mockMvc.perform(delete("/admin/users/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
