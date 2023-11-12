package ru.namelesscompany.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.namelesscompany.admin.dto.AdminDto;
import ru.namelesscompany.admin.dto.FullAdminDto;
import ru.namelesscompany.admin.service.AdminService;
import ru.namelesscompany.security.UserDetailsServ;
import ru.namelesscompany.security.role.model.Role;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminController.class)
@WithMockUser(value = "random name", authorities = "ROLE_OVERLORD")
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    UserDetailsServ userDetailsServ;
    @MockBean
    private AdminService adminService;

    private static final FullAdminDto fullAdminDto = new FullAdminDto();
    private static final AdminDto adminDto = new AdminDto();

    @BeforeAll
    public static void createAdmin() {
        fullAdminDto.setId(1L);
        fullAdminDto.setName("Admin");
        fullAdminDto.setEmail("admin@mail.admin");
        fullAdminDto.setPassword("111");
        fullAdminDto.setRegistrationDate(LocalDate.now());
        fullAdminDto.setOverlord(true);
        fullAdminDto.setRole(new Role(1L, "Role"));

        adminDto.setName("Admin");
        adminDto.setEmail("admin@mail.admin");
        adminDto.setRegistrationDate(LocalDate.now());
    }

    @Test
    public void shouldGetFullAdminInfo() throws Exception {
        when(adminService.getById(anyLong())).thenReturn(fullAdminDto);
        mockMvc.perform(get("/admin/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(fullAdminDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(fullAdminDto.getName())))
                .andExpect(jsonPath("$.registrationDate", is(fullAdminDto.getRegistrationDate().toString())))
                .andExpect(jsonPath("$.email", is(fullAdminDto.getEmail())))
                .andExpect(jsonPath("$.password", is(fullAdminDto.getPassword())))
                .andExpect(jsonPath("$.overlord", is(fullAdminDto.getOverlord())))
                .andExpect(jsonPath("$.role.id", is(fullAdminDto.getRole().getId().intValue())))
                .andExpect(jsonPath("$.role.name", is(fullAdminDto.getRole().getName())));
    }

    @Test
    public void shouldGetAdminInfo() throws Exception {
        when(adminService.getAdminForUsers(anyLong())).thenReturn(adminDto);
        mockMvc.perform(get("/admin//forAll/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(fullAdminDto.getName())))
                .andExpect(jsonPath("$.registrationDate", is(fullAdminDto.getRegistrationDate().toString())))
                .andExpect(jsonPath("$.email", is(fullAdminDto.getEmail())));
    }

    @Test
    public void shouldUpdateAdminData() throws Exception {
        when(adminService.update(any(), anyLong())).thenReturn(fullAdminDto);
        mockMvc.perform(patch("/admin/1")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(mapper.writeValueAsString(fullAdminDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(fullAdminDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(fullAdminDto.getName())))
                .andExpect(jsonPath("$.registrationDate", is(fullAdminDto.getRegistrationDate().toString())))
                .andExpect(jsonPath("$.email", is(fullAdminDto.getEmail())))
                .andExpect(jsonPath("$.password", is(fullAdminDto.getPassword())))
                .andExpect(jsonPath("$.overlord", is(fullAdminDto.getOverlord())))
                .andExpect(jsonPath("$.role.id", is(fullAdminDto.getRole().getId().intValue())))
                .andExpect(jsonPath("$.role.name", is(fullAdminDto.getRole().getName())));
    }
}
