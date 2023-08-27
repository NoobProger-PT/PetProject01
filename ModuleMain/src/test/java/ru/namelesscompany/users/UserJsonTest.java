package ru.namelesscompany.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.namelesscompany.user.dto.FullUserDto;
import ru.namelesscompany.user.dto.NewUserDto;
import ru.namelesscompany.user.dto.ShortUserDto;
import ru.namelesscompany.user.dto.UserDto;
import ru.namelesscompany.user.model.User;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
public class UserJsonTest {
    @Autowired
    JacksonTester<NewUserDto> newUserDtoJacksonTester;
    @Autowired
    JacksonTester<FullUserDto> fullUserDtoJacksonTester;
    @Autowired
    JacksonTester<UserDto> userDtoJacksonTester;
    @Autowired
    JacksonTester<ShortUserDto> shortUserDtoJacksonTester;
    @Autowired
    JacksonTester<User> userJacksonTester;

    @Test
    public void shouldReturnCorrectNewUserDtoJson() throws Exception {
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setId(10L);
        newUserDto.setName("name");
        newUserDto.setEmail("mail@mail.mail");

        JsonContent<NewUserDto> result = newUserDtoJacksonTester.write(newUserDto);
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(10);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("name");
        assertThat(result).extractingJsonPathStringValue("$.email").isEqualTo("mail@mail.mail");
    }

    @Test
    public void shouldReturnCorrectFullUserDtoJson() throws Exception {
        FullUserDto fullUserDto = new FullUserDto();
        fullUserDto.setId(5L);
        fullUserDto.setName("Full name");
        fullUserDto.setEmail("fullmail@mail.com");
        fullUserDto.setRegistrationDate(LocalDate.of(2022,6,23));

        JsonContent<FullUserDto> result = fullUserDtoJacksonTester.write(fullUserDto);
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(5);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Full name");
        assertThat(result).extractingJsonPathStringValue("$.email").isEqualTo("fullmail@mail.com");
        assertThat(result).extractingJsonPathStringValue("$.registrationDate").isEqualTo("2022-06-23");
    }

    @Test
    public void shouldReturnCorrectUserDtoJson() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("name");
        userDto.setEmail("mail@mail.mail");
        userDto.setRegistrationDate(LocalDate.of(2022,6,23));

        JsonContent<UserDto> result = userDtoJacksonTester.write(userDto);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("name");
        assertThat(result).extractingJsonPathStringValue("$.email").isEqualTo("mail@mail.mail");
        assertThat(result).extractingJsonPathStringValue("$.registrationDate").isEqualTo("2022-06-23");
    }

    @Test
    public void shouldReturnCorrectShortUserDtoJson() throws Exception {
        ShortUserDto shortUserDto = new ShortUserDto();
        shortUserDto.setName("name");

        JsonContent<ShortUserDto> result = shortUserDtoJacksonTester.write(shortUserDto);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("name");
    }

    @Test
    public void shouldReturnCorrectUserJson() throws Exception {
        User user = new User();
        user.setId(50L);
        user.setName("name");
        user.setEmail("mail@mail.mail");
        user.setRegistrationDate(LocalDate.of(2022,6,23));

        JsonContent<User> result = userJacksonTester.write(user);
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(50);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("name");
        assertThat(result).extractingJsonPathStringValue("$.email").isEqualTo("mail@mail.mail");
        assertThat(result).extractingJsonPathStringValue("$.registrationDate").isEqualTo("2022-06-23");
    }
}
