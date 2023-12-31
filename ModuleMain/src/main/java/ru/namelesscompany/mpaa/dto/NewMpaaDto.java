package ru.namelesscompany.mpaa.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class NewMpaaDto {
    @NotBlank
    @Length(max = 8)
    private String name;
}
