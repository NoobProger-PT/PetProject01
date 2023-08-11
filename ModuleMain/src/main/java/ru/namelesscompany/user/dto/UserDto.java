package ru.namelesscompany.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    //Данные пользователя, запрошенные другим зарегистрированныи пользователем
    private String name;
    private String email;
    private LocalDate registrationDate;
}
