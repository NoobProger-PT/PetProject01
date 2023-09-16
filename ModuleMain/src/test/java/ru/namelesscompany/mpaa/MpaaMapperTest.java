package ru.namelesscompany.mpaa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.namelesscompany.mpaa.dto.FullMpaaDto;
import ru.namelesscompany.mpaa.dto.MpaaDto;
import ru.namelesscompany.mpaa.dto.NewMpaaDto;
import ru.namelesscompany.mpaa.mapper.MpaaMapper;
import ru.namelesscompany.mpaa.model.Mpaa;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MpaaMapperTest {
    private static final Mpaa mpaa = new Mpaa();
    private static final NewMpaaDto newMpaaDto = new NewMpaaDto();

    @BeforeAll
    public static void createMpaa() {
        mpaa.setId(1L);
        mpaa.setName("Mpaa");

        newMpaaDto.setName("Mpaa");
    }

    @Test
    public void mapToMpaa() {
        Mpaa mpaa1 = MpaaMapper.mapToMpaa(newMpaaDto);
        assertEquals(newMpaaDto.getName(), mpaa1.getName());
    }

    @Test
    public void mapToMpaaDto() {
        MpaaDto mpaaDto = MpaaMapper.mapToMpaaDto(mpaa);
        assertEquals(mpaaDto.getName(), mpaa.getName());
    }

    @Test
    public void mapToFullMpaaDto() {
        FullMpaaDto fullMpaaDto = MpaaMapper.mapToFullMpaDto(mpaa);
        assertEquals(fullMpaaDto.getName(), mpaa.getName());
        assertEquals(fullMpaaDto.getId(), mpaa.getId());
    }
}
