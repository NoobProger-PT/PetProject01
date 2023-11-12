package ru.namelesscompany.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.namelesscompany.security.role.model.Role;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class FullAdminDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate registrationDate;
    private Boolean overlord;
    private String password;
    private Role role;
}
