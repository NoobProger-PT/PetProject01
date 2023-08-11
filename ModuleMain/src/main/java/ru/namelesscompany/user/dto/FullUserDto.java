package ru.namelesscompany.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class FullUserDto {
    //Данные пользователя, запрошенные админом
    private Long id;
    private String name;
    private String email;
    private LocalDate registrationDate;
}
