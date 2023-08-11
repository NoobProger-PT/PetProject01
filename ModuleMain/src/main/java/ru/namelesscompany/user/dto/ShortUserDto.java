package ru.namelesscompany.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShortUserDto {
    //Данные пользователя, запрошенные гостем
    private String name;
}
