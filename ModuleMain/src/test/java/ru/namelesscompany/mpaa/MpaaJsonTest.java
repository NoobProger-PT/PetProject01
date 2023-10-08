package ru.namelesscompany.mpaa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.namelesscompany.mpaa.dto.FullMpaaDto;
import ru.namelesscompany.mpaa.dto.MpaaDto;
import ru.namelesscompany.mpaa.dto.NewMpaaDto;
import ru.namelesscompany.mpaa.model.Mpaa;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
public class MpaaJsonTest {
    @Autowired
    JacksonTester<Mpaa> mpaaJacksonTester;
    @Autowired
    JacksonTester<MpaaDto> mpaaDtoJacksonTester;
    @Autowired
    JacksonTester<FullMpaaDto> fullMpaaDtoJacksonTester;
    @Autowired
    JacksonTester<NewMpaaDto> newMpaaDtoJacksonTester;

    @Test
    public void shouldReturnCorrectMpaaJson() throws Exception {
        Mpaa mpaa = new Mpaa();
        mpaa.setId(1L);
        mpaa.setName("Mpaa");

        JsonContent<Mpaa> result = mpaaJacksonTester.write(mpaa);
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Mpaa");
    }

    @Test
    public void shouldReturnCorrectMpaaDtoJson() throws Exception {
        MpaaDto mpaaDto = new MpaaDto();
        mpaaDto.setName("Mpaa");

        JsonContent<MpaaDto> result = mpaaDtoJacksonTester.write(mpaaDto);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Mpaa");
    }

    @Test
    public void shouldReturnCorrectFullMpaaDtoJson() throws Exception {
        FullMpaaDto fullMpaaDto = new FullMpaaDto();
        fullMpaaDto.setId(1L);
        fullMpaaDto.setName("Mpaa");

        JsonContent<FullMpaaDto> result = fullMpaaDtoJacksonTester.write(fullMpaaDto);
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Mpaa");
    }

    @Test
    public void shouldReturnCorrectNewMpaaDtoJson() throws Exception {
        NewMpaaDto newMpaaDto = new NewMpaaDto();
        newMpaaDto.setName("Mpaa");

        JsonContent<NewMpaaDto> result = newMpaaDtoJacksonTester.write(newMpaaDto);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Mpaa");
    }
}
