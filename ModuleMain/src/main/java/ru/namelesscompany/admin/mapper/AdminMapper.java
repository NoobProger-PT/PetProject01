package ru.namelesscompany.admin.mapper;

import ru.namelesscompany.admin.dto.FullAdminDto;
import ru.namelesscompany.admin.dto.NewAdminDto;
import ru.namelesscompany.admin.dto.AdminDto;
import ru.namelesscompany.admin.model.Admin;
import ru.namelesscompany.security.role.model.Role;

import java.time.LocalDate;

public class AdminMapper {
    public static Admin mapToAdmin(NewAdminDto newAdminDto) {
        Admin admin = new Admin();
        admin.setName(newAdminDto.getName());
        admin.setEmail(newAdminDto.getEmail());
        admin.setRegistrationDate(LocalDate.now());
        admin.setOverlord(false);
        admin.setPassword(newAdminDto.getPassword());
        admin.setRole(new Role(1L, "Admin"));
        return admin;
    }

    public static FullAdminDto mapToFullAdminDto(Admin admin) {
        FullAdminDto fullAdminDto = new FullAdminDto();
        fullAdminDto.setId(admin.getId());
        fullAdminDto.setName(admin.getName());
        fullAdminDto.setEmail(admin.getEmail());
        fullAdminDto.setRegistrationDate(admin.getRegistrationDate());
        fullAdminDto.setPassword(admin.getPassword());
        fullAdminDto.setOverlord(admin.getOverlord());
        fullAdminDto.setRole(admin.getRole());
        return fullAdminDto;
    }

    public static AdminDto mapToAdminDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setName(admin.getName());
        adminDto.setEmail(admin.getEmail());
        adminDto.setRegistrationDate(admin.getRegistrationDate());
        return adminDto;
    }
}
