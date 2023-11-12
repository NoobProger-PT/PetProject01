package ru.namelesscompany.subAdmin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.namelesscompany.Marker;
import ru.namelesscompany.subAdmin.dto.FullSubAdminDto;
import ru.namelesscompany.subAdmin.dto.NewSubAdminDto;
import ru.namelesscompany.subAdmin.service.privateService.PrivateSubAdminService;

import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/private/subAdmin")
@Slf4j
@Validated
public class PrivateSubAdminController {
    private final PrivateSubAdminService service;

    @GetMapping("/{subAdminId}")
    public FullSubAdminDto get(@PathVariable @Positive Long subAdminId) {
        log.info("Получение субадмина по ID.");
        return service.getById(subAdminId);
    }

    @PatchMapping("/{subAdminId}")
    public FullSubAdminDto update(@PathVariable @Positive Long subAdminId,
                                  @Validated({Marker.Update.class}) @RequestBody NewSubAdminDto updatedSubAdmin) {
        log.info("Субадмин редактирует данные, ID: {}", subAdminId);
        return service.update(updatedSubAdmin, subAdminId);
    }
}
