package ru.namelesscompany.subAdmin.mapper;

import ru.namelesscompany.security.role.model.Role;
import ru.namelesscompany.subAdmin.dto.FullSubAdminDto;
import ru.namelesscompany.subAdmin.dto.NewSubAdminDto;
import ru.namelesscompany.subAdmin.dto.SubAdminDto;
import ru.namelesscompany.subAdmin.model.SubAdmin;

import java.time.LocalDate;

public class SubAdminMapper {
    public static SubAdmin mapToSubAdmin(NewSubAdminDto newSubAdminDto) {
        SubAdmin subAdmin = new SubAdmin();
        subAdmin.setName(newSubAdminDto.getName());
        subAdmin.setEmail(newSubAdminDto.getEmail());
        subAdmin.setRegistrationDate(LocalDate.now());
        subAdmin.setPassword(newSubAdminDto.getPassword());
        subAdmin.setRole(new Role(2L, "ROLE_SUBADMIN"));
        subAdmin.setOverlord(false);
        return subAdmin;
    }

    public static FullSubAdminDto mapToFullSubAdminDto(SubAdmin subAdmin) {
        FullSubAdminDto fullSubAdminDto = new FullSubAdminDto();
        fullSubAdminDto.setId(subAdmin.getId());
        fullSubAdminDto.setName(subAdmin.getName());
        fullSubAdminDto.setEmail(subAdmin.getEmail());
        fullSubAdminDto.setRegistrationDate(subAdmin.getRegistrationDate());
        fullSubAdminDto.setPassword(subAdmin.getPassword());
        fullSubAdminDto.setOverlord(subAdmin.getOverlord());
        fullSubAdminDto.setRole(subAdmin.getRole());
        return fullSubAdminDto;
    }

    public static SubAdminDto mapToSubAdminDto(SubAdmin subAdmin) {
        SubAdminDto subAdminDto = new SubAdminDto();
        subAdminDto.setName(subAdmin.getName());
        subAdminDto.setEmail(subAdmin.getEmail());
        subAdminDto.setRegistrationDate(subAdmin.getRegistrationDate());
        return subAdminDto;
    }
}
