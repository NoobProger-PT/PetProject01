package ru.namelesscompany.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.namelesscompany.admin.dto.AdminDto;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserDto extends AdminDto {
    //Данные пользователя, запрошенные другим зарегистрированныи пользователем
}
