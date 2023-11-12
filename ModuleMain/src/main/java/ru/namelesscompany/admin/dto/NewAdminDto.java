package ru.namelesscompany.admin.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import ru.namelesscompany.Marker;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class NewAdminDto {
    @NotBlank(groups = {Marker.Create.class})
    @Length(max = 20, groups = {Marker.Create.class, Marker.Update.class})
    private String name;
    @NotBlank(groups = {Marker.Create.class})
    @Email(groups = {Marker.Create.class, Marker.Update.class})
    private String email;
    @NotBlank(groups = {Marker.Create.class})
    @Length(max = 20, groups = {Marker.Create.class, Marker.Update.class})
    private String password;
}
