package ru.namelesscompany.admin;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.namelesscompany.admin.dto.AdminDto;
import ru.namelesscompany.admin.dto.FullAdminDto;
import ru.namelesscompany.admin.dto.NewAdminDto;
import ru.namelesscompany.admin.mapper.AdminMapper;
import ru.namelesscompany.admin.model.Admin;
import ru.namelesscompany.security.role.model.Role;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminMapperTest {
    private static final Admin admin = new Admin();
    private static final NewAdminDto newAdminDto = new NewAdminDto();

    @BeforeAll
    public static void createAdmin() {
        admin.setId(1L);
        admin.setName("Admin");
        admin.setEmail("admin@mail.admin");
        admin.setPassword("111");
        admin.setRegistrationDate(LocalDate.now());
        admin.setOverlord(false);
        admin.setRole(new Role(1L, "Role"));

        newAdminDto.setName("new Admin");
        newAdminDto.setEmail("newAd@mail.new");
        newAdminDto.setPassword("222");
    }

    @Test
    public void mapToAdmin() {
        Admin admin1 = AdminMapper.mapToAdmin(newAdminDto);
        assertEquals(admin1.getName(), newAdminDto.getName());
        assertEquals(admin1.getEmail(), newAdminDto.getEmail());
        assertEquals(admin1.getPassword(), newAdminDto.getPassword());
        assertEquals(admin1.getRegistrationDate(), LocalDate.now());
        assertEquals(admin1.getOverlord(), false);
        assertEquals(admin1.getRole().getName(), "Admin");
    }

    @Test
    public void mapToFullAdminDto() {
        FullAdminDto fullAdminDto = AdminMapper.mapToFullAdminDto(admin);
        assertEquals(fullAdminDto.getId(), admin.getId());
        assertEquals(fullAdminDto.getName(), admin.getName());
        assertEquals(fullAdminDto.getEmail(), admin.getEmail());
        assertEquals(fullAdminDto.getPassword(), admin.getPassword());
        assertEquals(fullAdminDto.getRegistrationDate(), LocalDate.now());
        assertEquals(fullAdminDto.getOverlord(), false);
        assertEquals(fullAdminDto.getRole().getName(), "Role");
        assertEquals(fullAdminDto.getRole().getId(), 1L);
    }

    @Test
    public void mapToAdminDto() {
        AdminDto adminDto = AdminMapper.mapToAdminDto(admin);
        assertEquals(adminDto.getName(), admin.getName());
        assertEquals(adminDto.getEmail(), admin.getEmail());
        assertEquals(adminDto.getRegistrationDate(), LocalDate.now());
    }
}
