package ru.namelesscompany.subAdmin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.namelesscompany.Marker;
import ru.namelesscompany.subAdmin.dto.FullSubAdminDto;
import ru.namelesscompany.subAdmin.dto.NewSubAdminDto;
import ru.namelesscompany.subAdmin.service.adminService.AdminSubAdminService;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/subAdmin")
@Slf4j
@Validated
public class AdminSubAdminController {

    private final AdminSubAdminService service;

    @PostMapping
    public FullSubAdminDto add(@Validated({Marker.Create.class}) @RequestBody NewSubAdminDto newSubAdminDto) {
        log.info("Добавление нового субадмина с именем: {} и Email: {}", newSubAdminDto.getName(), newSubAdminDto.getEmail());
        return service.add(newSubAdminDto);
    }

    @GetMapping("/{subAdminId}")
    public FullSubAdminDto get(@PathVariable @Positive Long subAdminId) {
        log.info("Получение субадмина по ID админом.");
        return service.getById(subAdminId);
    }

    @GetMapping("/all")
    public List<FullSubAdminDto> getAll() {
        log.info("Получение всех субадминов админом");
        return service.getAll();
    }

    @GetMapping("/several")
    public List<FullSubAdminDto> getByIds(@RequestParam(defaultValue = "") List<Long> ids) {
        log.info("Получение субадминов админом с ID: {}", ids);
        return service.getByIds(ids);
    }

    @PatchMapping("/{subAdminId}")
    public FullSubAdminDto update(@PathVariable @Positive Long subAdminId,
                              @Validated({Marker.Update.class}) @RequestBody NewSubAdminDto updatedSubAdmin) {
        log.info("Админ редактирует данные субадмина с ID: {}", subAdminId);
        return service.update(updatedSubAdmin, subAdminId);
    }

    @DeleteMapping("/{subAdminId}")
    public String delete(@PathVariable @Positive Long subAdminId) {
        log.info("Удаление субадмина админом.");
        return service.delete(subAdminId);
    }
}
