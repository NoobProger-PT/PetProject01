package ru.namelesscompany.users;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.namelesscompany.user.dto.FullUserDto;
import ru.namelesscompany.user.dto.NewUserDto;
import ru.namelesscompany.user.dto.ShortUserDto;
import ru.namelesscompany.user.dto.UserDto;
import ru.namelesscompany.user.mapper.UserMapper;
import ru.namelesscompany.user.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {
    private static final User user = new User();
    private static final NewUserDto newUserDto = new NewUserDto();

    @BeforeAll
    public static void createUsers() {
        user.setId(23L);
        user.setName("Sample name");
        user.setEmail("sample@mail.smpl");
        user.setRegistrationDate(LocalDate.of(2022,6,23));

        newUserDto.setName("Name1");
        newUserDto.setEmail("mail2@mail.com");
    }

    @Test
    public void mapToUser() {
        User user1 = UserMapper.mapToUser(newUserDto);
        assertEquals(newUserDto.getEmail(), user1.getEmail());
        assertEquals(newUserDto.getName(), user1.getName());
        assertEquals(LocalDate.now(), user1.getRegistrationDate());
    }

    @Test
    public void mapToFullUserDto() {
        FullUserDto fullUserDto = UserMapper.mapToUserFullDto(user);
        assertEquals(fullUserDto.getEmail(), user.getEmail());
        assertEquals(fullUserDto.getName(), user.getName());
        assertEquals(fullUserDto.getId(), user.getId());
        assertEquals(fullUserDto.getRegistrationDate(), user.getRegistrationDate());
    }

    @Test
    public void mapToUserDto() {
        UserDto userDto = UserMapper.mapToUserDto(user);
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getRegistrationDate(), user.getRegistrationDate());
    }

    @Test
    public void mapToShorUserDto() {
        ShortUserDto shortUserDto = UserMapper.mapToUserShortDto(user);
        assertEquals(shortUserDto.getName(), user.getName());
    }
}
