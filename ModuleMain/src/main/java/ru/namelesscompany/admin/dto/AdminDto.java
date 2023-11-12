package ru.namelesscompany.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AdminDto {
    private String name;
    private String email;
    private LocalDate registrationDate;
}
