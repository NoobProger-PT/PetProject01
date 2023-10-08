package ru.namelesscompany.mpaa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.namelesscompany.exceptions.mpaa.MpaaNotFound;
import ru.namelesscompany.mpaa.dto.NewMpaaDto;
import ru.namelesscompany.mpaa.model.Mpaa;
import ru.namelesscompany.mpaa.repository.MpaaRepository;
import ru.namelesscompany.mpaa.service.adminService.AdminMpaaServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class AdminMpaaServiceTest {
    private AdminMpaaServiceImpl mpaaService;
    private MpaaRepository mpaaRepository;
    private final Mpaa mpaa = new Mpaa();

    @BeforeEach
    public void setUp() {
        mpaaRepository = mock(MpaaRepository.class);
        mpaaService = new AdminMpaaServiceImpl(mpaaRepository);
        when(mpaaRepository.save(any())).then(invocation -> invocation.getArgument(0));

        mpaa.setId(1L);
        mpaa.setName("Mpaa 1");
    }

    @Test
    public void shouldAddNewMpaa() {
        when(mpaaRepository.save(any())).thenReturn(mpaa);
        var result = mpaaService.add(new NewMpaaDto());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(mpaa.getId(), result.getId());
        Assertions.assertEquals(mpaa.getName(), result.getName());
    }

    @Test
    public void shouldGetById() {
        when(mpaaRepository.findById(anyLong())).thenReturn(Optional.of(mpaa));
        var result = mpaaService.getById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(mpaa.getId(), result.getId());
        Assertions.assertEquals(mpaa.getName(), result.getName());
    }

    @Test
    public void shouldNotGetById() {
        Assertions.assertThrows(MpaaNotFound.class, () -> mpaaService.getById(10L));
    }

    @Test
    public void shouldGetAll() {
        when(mpaaRepository.findAll()).thenReturn(List.of(mpaa));
        var result = mpaaService.getAll();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(mpaa.getId(), result.get(0).getId());
        Assertions.assertEquals(mpaa.getName(), result.get(0).getName());
    }

    @Test
    public void shouldChange() {
        NewMpaaDto newMpaaDto = new NewMpaaDto();
        newMpaaDto.setName("change mpaa");
        when(mpaaRepository.findById(anyLong())).thenReturn(Optional.of(mpaa));
        var result = mpaaService.change(newMpaaDto, 1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(mpaa.getId(), result.getId());
        Assertions.assertEquals(newMpaaDto.getName(), result.getName());
    }

    @Test
    public void shouldNotChangeWithWrongId() {
        NewMpaaDto newMpaaDto = new NewMpaaDto();
        newMpaaDto.setName("change mpaa");
        Assertions.assertThrows(MpaaNotFound.class, () -> mpaaService.change(newMpaaDto, 10L));
    }

    @Test
    public void shouldDeleteMpaa() {
        when(mpaaRepository.findById(anyLong())).thenReturn(Optional.of(mpaa));
        Assertions.assertEquals(mpaaService.delete(1L), "Рейтинг Mpaa удален.");
        verify(mpaaRepository, times(1)).deleteById(any());
    }

    @Test
    public void shouldNotDeleteByWrongId() {
        Assertions.assertThrows(MpaaNotFound.class, () -> mpaaService.delete(10L));
        verify(mpaaRepository, times(0)).deleteById(any());
    }
}
