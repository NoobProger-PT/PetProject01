package ru.namelesscompany.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.namelesscompany.admin.dto.AdminDto;
import ru.namelesscompany.admin.dto.FullAdminDto;
import ru.namelesscompany.admin.dto.NewAdminDto;
import ru.namelesscompany.security.role.model.Role;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@JsonTest
public class AdminJsonTest {
    @Autowired
    JacksonTester<NewAdminDto> newAdminDtoJacksonTester;
    @Autowired
    JacksonTester<FullAdminDto> fullAdminDtoJacksonTester;
    @Autowired
    JacksonTester<AdminDto> adminDtoJacksonTester;

    @Test
    public void shouldReturnCorrectNewAdminDtoJson() throws Exception {
        NewAdminDto newAdminDto = new NewAdminDto();
        newAdminDto.setName("Test Admin");
        newAdminDto.setEmail("test@mail.test");
        newAdminDto.setPassword("111");

        JsonContent<NewAdminDto> result = newAdminDtoJacksonTester.write(newAdminDto);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Test Admin");
        assertThat(result).extractingJsonPathStringValue("$.email").isEqualTo("test@mail.test");
        assertThat(result).extractingJsonPathStringValue("$.password").isEqualTo("111");
    }

    @Test
    public void shouldReturnCorrectFullAdminDtoJson() throws Exception {
        FullAdminDto fullAdminDto = new FullAdminDto();
        fullAdminDto.setId(1L);
        fullAdminDto.setName("Test Admin");
        fullAdminDto.setEmail("test@mail.test");
        fullAdminDto.setPassword("111");
        fullAdminDto.setRegistrationDate(LocalDate.now());
        fullAdminDto.setOverlord(true);
        fullAdminDto.setRole(new Role(1L, "Role"));

        JsonContent<FullAdminDto> result = fullAdminDtoJacksonTester.write(fullAdminDto);
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Test Admin");
        assertThat(result).extractingJsonPathStringValue("$.email").isEqualTo("test@mail.test");
        assertThat(result).extractingJsonPathStringValue("$.password").isEqualTo("111");
        assertThat(result).extractingJsonPathStringValue("$.registrationDate").isEqualTo(LocalDate.now().toString());
        assertThat(result).extractingJsonPathBooleanValue("$.overlord").isEqualTo(true);
        assertThat(result).extractingJsonPathNumberValue("$.role.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.role.name").isEqualTo("Role");
    }

    @Test
    public void shouldReturnCorrectAdminDtoJson() throws Exception {
        AdminDto adminDto = new AdminDto();
        adminDto.setName("Test Admin");
        adminDto.setEmail("test@mail.test");
        adminDto.setRegistrationDate(LocalDate.now());

        JsonContent<AdminDto> result = adminDtoJacksonTester.write(adminDto);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Test Admin");
        assertThat(result).extractingJsonPathStringValue("$.email").isEqualTo("test@mail.test");
        assertThat(result).extractingJsonPathStringValue("$.registrationDate").isEqualTo(LocalDate.now().toString());
    }



}
