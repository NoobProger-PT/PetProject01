package ru.namelesscompany.mpaa.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.namelesscompany.mpaa.dto.MpaaDto;
import ru.namelesscompany.mpaa.service.privateService.PrivateMpaaService;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/private/mpaa")
@Slf4j
@Validated
public class MpaaPrivateController {

    private final PrivateMpaaService service;

    @GetMapping("/{mpaaId}")
    public MpaaDto get(@PathVariable @Positive long mpaaId) {
        log.info("Получение пользователем рейтинга Mpaa с id: {}", mpaaId);
        return service.getById(mpaaId);
    }

    @GetMapping("/all")
    public List<MpaaDto> getAll() {
        log.info("Получение пользователем всех райтингов Mpaa");
        return service.getAll();
    }
}
