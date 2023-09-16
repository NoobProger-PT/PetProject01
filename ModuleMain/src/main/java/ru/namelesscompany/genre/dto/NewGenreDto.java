package ru.namelesscompany.genre.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class NewGenreDto {
    @NotBlank
    @Length(min = 1, max = 20)
    private String name;
}
