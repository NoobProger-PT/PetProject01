package ru.namelesscompany.user.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import ru.namelesscompany.Marker;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class NewUserDto {
    //Форма для нового пользователя или его редактирования
    private long id;
    @NotBlank(groups = {Marker.Create.class})
    @Length(min = 1, max = 30, groups = {Marker.Create.class, Marker.Update.class})
    private String name;
    @NotBlank(groups = {Marker.Create.class})
    @Email(groups = {Marker.Create.class, Marker.Update.class})
    private String email;

}
