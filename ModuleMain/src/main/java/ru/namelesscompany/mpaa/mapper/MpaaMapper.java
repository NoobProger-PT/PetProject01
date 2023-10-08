package ru.namelesscompany.mpaa.mapper;

import ru.namelesscompany.mpaa.dto.FullMpaaDto;
import ru.namelesscompany.mpaa.dto.MpaaDto;
import ru.namelesscompany.mpaa.dto.NewMpaaDto;
import ru.namelesscompany.mpaa.model.Mpaa;

public class MpaaMapper {
    public static Mpaa mapToMpaa(NewMpaaDto newMpaaDto) {
        Mpaa mpaa = new Mpaa();
        mpaa.setName(newMpaaDto.getName());
        return mpaa;
    }

    public static FullMpaaDto mapToFullMpaDto(Mpaa mpaa) {
        FullMpaaDto fullMpaaDto = new FullMpaaDto();
        fullMpaaDto.setId(mpaa.getId());
        fullMpaaDto.setName(mpaa.getName());
        return fullMpaaDto;
    }

    public static MpaaDto mapToMpaaDto(Mpaa mpaa) {
        MpaaDto mpaaDto = new MpaaDto();
        mpaaDto.setName(mpaa.getName());
        return mpaaDto;
    }
}
