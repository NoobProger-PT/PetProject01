package ru.namelesscompany.mpaa.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.namelesscompany.mpaa.dto.FullMpaaDto;
import ru.namelesscompany.mpaa.dto.NewMpaaDto;
import ru.namelesscompany.mpaa.service.adminService.AdminMpaaService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/mpaa")
@Slf4j
@Validated
public class MpaaAdminController {

    private final AdminMpaaService service;

    @PostMapping
    public FullMpaaDto add(@RequestBody @Valid NewMpaaDto newMpaaDto) {
        log.info("Добавление нового рейтинга админом Mpaa с названием: {}", newMpaaDto.getName());
        return service.add(newMpaaDto);
    }

    @GetMapping("/{mpaaId}")
    public FullMpaaDto get(@PathVariable @Positive long mpaaId) {
        log.info("Получение админом рейтинга Mpaa с id: {}", mpaaId);
        return service.getById(mpaaId);
    }

    @GetMapping("/all")
    public List<FullMpaaDto> getAll() {
        log.info("Получение админом всех райтингов Mpaa");
        return service.getAll();
    }

    @PatchMapping("/{mpaaId}")
    public FullMpaaDto change(@PathVariable @Positive long mpaaId,
                              @RequestBody NewMpaaDto newMpaaDto) {
        log.info("Изменение админом рейтинга Mpaa с id: {}", mpaaId);
        return service.change(newMpaaDto, mpaaId);
    }

    @DeleteMapping("/{mpaaId}")
    public String delete(@PathVariable @Positive long mpaaId) {
        log.info("Уданение админом рейтинга Mpaa с id: {}", mpaaId);
        return service.delete(mpaaId);
    }
}
