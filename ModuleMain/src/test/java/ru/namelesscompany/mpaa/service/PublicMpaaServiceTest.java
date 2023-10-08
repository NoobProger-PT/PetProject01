package ru.namelesscompany.mpaa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.namelesscompany.exceptions.mpaa.MpaaNotFound;
import ru.namelesscompany.mpaa.model.Mpaa;
import ru.namelesscompany.mpaa.repository.MpaaRepository;
import ru.namelesscompany.mpaa.service.publicService.PublicMpaaServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PublicMpaaServiceTest {
    private PublicMpaaServiceImpl mpaaService;
    private MpaaRepository mpaaRepository;
    private final Mpaa mpaa = new Mpaa();

    @BeforeEach
    public void setUp() {
        mpaaRepository = mock(MpaaRepository.class);
        mpaaService = new PublicMpaaServiceImpl(mpaaRepository);
        when(mpaaRepository.save(any())).then(invocation -> invocation.getArgument(0));

        mpaa.setId(1L);
        mpaa.setName("Mpaa 1");
    }

    @Test
    public void shouldGetById() {
        when(mpaaRepository.findById(anyLong())).thenReturn(Optional.of(mpaa));
        var result = mpaaService.getById(1L);
        Assertions.assertNotNull(result);
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
        Assertions.assertEquals(mpaa.getName(), result.get(0).getName());
    }
}
